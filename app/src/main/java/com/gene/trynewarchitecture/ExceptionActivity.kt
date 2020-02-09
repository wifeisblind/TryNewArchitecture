package com.gene.trynewarchitecture

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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

        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
                listView.visibility = View.INVISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
                listView.visibility = View.VISIBLE
            }
        })


        viewModel.exceptionList.observe(this, Observer { taskList ->
            listView.adapter = ArrayAdapter(this, R.layout.lay_exception_list, R.id.text_view_exception, taskList)
        })
    }

}