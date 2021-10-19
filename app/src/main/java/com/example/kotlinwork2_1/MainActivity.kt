package com.example.kotlinwork2_1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post.*


class MainActivity : AppCompatActivity() {
    private lateinit var posadapter: Adapter
    lateinit var post: Post
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        addRecord()
    }
    fun init(){
        recyclerView.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = Adapter()
            posadapter = Adapter()
            adapter = posadapter

        }

    }
    fun addRecord(){
        val data = Recording.getDataBase()
        val iterator = data.listIterator()
        while (iterator.hasNext()){
            if(iterator.next().hidePost){
                iterator.remove()
            }
        }
       posadapter.submiDataList(data)
    }
}
