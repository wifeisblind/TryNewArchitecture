package com.gene.trynewarchitecture

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gene.trynewarchitecture.api.ApiSuccessResponse
import com.gene.trynewarchitecture.api.NetworkService
import com.gene.trynewarchitecture.api.request.ExceptionListRequest
import com.gene.trynewarchitecture.utils.EncryptionUtils
import com.gene.trynewarchitecture.utils.EncryptionUtils.getDescriptionBySecretKey
import com.gene.trynewarchitecture.utils.EncryptionUtils.getEncryptionBySecretKey
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.tms.twjoin.twjoin_tms_android.utils.LiveDataTestUtil.getValue
import io.mockk.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.`is`
import okio.buffer
import okio.source
import org.hamcrest.MatcherAssert.assertThat
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NetworkServiceTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var networkService: NetworkService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        val client = OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        }
        networkService = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()
                .create(NetworkService::class.java)
        mockAESEncryption()
    }

    @Test
    fun `test getExceptionList work well with liveData`() {
        enqueueResponse("exceptionlist.json")
        val exceptionListResponse = (getValue(networkService.getExceptionList(ExceptionListRequest("DR"))) as ApiSuccessResponse).body

        val request = mockWebServer.takeRequest()
        assertThat(request.path, `is`("/api/get_exception_list"))
        assertThat(request.body.peek().readUtf8(), `is`(""""${Gson().toJson(ExceptionListRequest("DR"))}""""))
        assert(exceptionListResponse.exceptions.isNotEmpty())
        assertThat(exceptionListResponse.exceptions[14].code, `is`("99"))
    }

    @Test
    fun `test getExceptionList when api inner level error return api error`() {
        enqueueResponse("api_error.json")

        val errorMessage = (getValue(networkService.getExceptionList(ExceptionListRequest("DR"))) as ApiErrorResponse).errorMessage

        assertThat(errorMessage, `is`("API Error: user not login"))
    }

    @Test
    fun `test getExceptionList when api outer level error return api error`() {
        val json = JsonObject().apply {
            addProperty("result", "failure")
            addProperty("error_message", "user not login")
        }

        mockWebServer.enqueue(
                MockResponse().setBody(json.toString())
        )

        val errorMessage = (getValue(networkService.getExceptionList(ExceptionListRequest("DR"))) as ApiErrorResponse).errorMessage

        assertThat(errorMessage, `is`("API Error: user not login"))
    }

    @Test
    fun `test getExceptionList http error return http error`() {
        mockWebServer.enqueue(
                MockResponse().setStatus("""HTTP/1.1 404""").setBody("404 Not Found")
        )
        val errorMessage = (getValue(networkService.getExceptionList(ExceptionListRequest("DR"))) as ApiErrorResponse).errorMessage

        assertThat(errorMessage, `is`("404 Not Found"))
    }

    @After
    fun afterClass() {
        mockWebServer.shutdown()
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }

        val json = JsonObject().apply {
            addProperty("result", source.readString(Charsets.UTF_8))
            addProperty("error_message", "")
        }


        mockWebServer.enqueue(
                mockResponse.setBody(json.toString())
        )
    }

    private fun mockAESEncryption() {
        mockkStatic(EncryptionUtils::class)
        val slotE = slot<String>()
        val slotD = slot<String>()
        every {
            getEncryptionBySecretKey(capture(slotE))
        } answers {
            slotE.captured
        }
        every {
            getDescriptionBySecretKey(capture(slotD))
        } answers {
            slotD.captured
        }
    }
}