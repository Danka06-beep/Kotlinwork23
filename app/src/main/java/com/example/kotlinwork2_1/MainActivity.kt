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
            val data = ArrayList<Post>()
            var temp = 0
            for (i in 0..dataPost.size - 1) {
                data.add(dataPost[i])
                temp++
            }
            posadapter.submiDataList(data as ArrayList<Post>)
            posadapter.notifyItemRangeInserted(0, data.size)
            withContext(Main) {
                progressBar.visibility = ProgressBar.GONE
            }
        }

    }
}
