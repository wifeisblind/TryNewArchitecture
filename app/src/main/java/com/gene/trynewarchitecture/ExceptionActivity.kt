package com.gene.trynewarchitecture

import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gene.trynewarchitecture.viewmodel.ExceptionViewModel
import kotlinx.android.synthetic.main.activity_exceptoin.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExceptionActivity : AppCompatActivity() {

    private val viewModel: ExceptionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exceptoin)

    }

}