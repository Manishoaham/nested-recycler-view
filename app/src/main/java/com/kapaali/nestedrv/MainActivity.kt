package com.kapaali.nestedrv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvNested : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_nested_rv)
        rvNested = findViewById(R.id.rv_nested_main)

        val data = mutableListOf<NestedData>()
        for(i in 1..50){
            val nd = NestedData()
            nd.title = "Item $i"
            for(j in 1..20){
                val ind = NestedData()
                ind.title = "Item $i:$j"
                nd.nestedData.add(ind)
            }
            data.add(nd)
        }

        rvNested.layoutManager = LinearLayoutManager(this)
        rvNested.adapter = NestedAdapter(data)
    }
}