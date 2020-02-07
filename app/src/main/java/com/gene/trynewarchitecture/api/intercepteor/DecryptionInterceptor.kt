package com.gene.trynewarchitecture.api.intercepteor

import com.gene.trynewarchitecture.api.HTTP_API_ERROR
import com.gene.trynewarchitecture.api.response.BaseResponseV2
import com.gene.trynewarchitecture.extensions.convertToString
import com.gene.trynewarchitecture.utils.EncryptionUtils
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody

class DecryptionInterceptor : Interceptor {

    private var baseResponse: BaseResponseV2? = null

    val gson = Gson()

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldResponse = chain.proceed(chain.request())

        if (!oldResponse.isSuccessful) {
            return oldResponse
        }

        val body = oldResponse.body?.byteStream()?.convertToString()

        try {
            baseResponse = gson.fromJson(body, BaseResponseV2::class.java)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            return oldResponse
        }

        if (baseResponse!!.result == "failure") {
            return oldResponse.newBuilder()
                    .code(HTTP_API_ERROR)
                    .body("API Error: ${baseResponse!!.errorMessage}".toResponseBody("application/json".toMediaType()))
                    .build()
        } else {

            val result = EncryptionUtils.getDescriptionBySecretKey(baseResponse!!.result)
            try {
                baseResponse = gson.fromJson(result, BaseResponseV2::class.java)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
                return oldResponse
            }

            return if (baseResponse!!.result == "failure") {
                oldResponse.newBuilder()
                        .code(HTTP_API_ERROR)
                        .body("API Error: ${baseResponse!!.errorMessage}".toResponseBody("application/json".toMediaType()))
                        .build()
            } else {
                oldResponse.newBuilder()
                        .body(result.toResponseBody("application/json".toMediaType()))
                        .build()
            }
        }
    }
}