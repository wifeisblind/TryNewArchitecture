package com.gene.trynewarchitecture

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gene.trynewarchitecture.utils.Status
import com.gene.trynewarchitecture.viewmodel.ExceptionViewModel
import kotlinx.android.synthetic.main.activity_exceptoin.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExceptionActivity : AppCompatActivity() {

    private val viewModel: ExceptionViewModel by viewModel()

    private val taskNo: String = "200000000304"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exceptoin)

        viewModel.setTaskNo(taskNo)

        viewModel.status.observe(this, Observer { status ->
            when(status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.INVISIBLE
                    listView.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    listView.visibility = View.INVISIBLE
                }
                else -> {}
            }
        })


        viewModel.exceptionList.observe(this, Observer { taskList ->
            listView.adapter = ArrayAdapter(this, R.layout.lay_exception_list, R.id.text_view_exception, taskList)
        })
    }

}