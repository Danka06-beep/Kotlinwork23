package com.example.kotlinwork2_1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var posadapter: Adapter
    lateinit var post: Post
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.visibility = ProgressBar.VISIBLE
        init()
        addRecord()

    }

    fun init() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = Adapter()
            posadapter = Adapter()
            adapter = posadapter

        }

    }
    fun addRecord() {
        lifecycleScope.launch {
            val dataPost = Recording.getPosts()
                .filter {
                    !it.hidePost
                }
            val dataRecording = Recording.getPostAdvertising()
            val data = ArrayList<Post>()
            var temp = 0
            var tempPost = 0
            for (element in dataPost) {
                data.add(element)
                temp++
                if (temp == 1 && tempPost < dataRecording.size) {
                    temp = 0
                    data.add(dataRecording[tempPost])
                    tempPost++
                }
            }
            posadapter.submiDataList(data)
            posadapter.notifyItemRangeInserted(0, data.size)
            withContext(Main) {
                progressBar.visibility = ProgressBar.GONE
            }
        }

    }
}
