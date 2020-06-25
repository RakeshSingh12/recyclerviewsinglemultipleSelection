package com.example.myapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sdei.woundspro.recyclerviewsinglemultiple.ItemAdapter
import java.util.ArrayList

class MainActivity : AppCompatActivity()  ,ItemAdapter.OnClickAction{
    var activity: Activity = this@MainActivity
    var rv: RecyclerView? = null
    var chk: CheckBox? = null
    var adapter: ItemAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var items: MutableList<Item> = ArrayList()


        rv = findViewById(R.id.rcView)
        chk = findViewById(R.id.chk)



        chk!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                adapter!!.selectAll()
                Log.e("onCreate", items.size.toString())
            } else {
                adapter!!.clearSelected()
                Log.e("onCreate2", items.size.toString())
            }
        }

        val lm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = ItemAdapter(this, this)
        rv?.adapter = adapter
        rv?.setHasFixedSize(true)
        rv?.layoutManager = lm
        val dividerItemDecoration = DividerItemDecoration(rv?.context, lm.orientation)
        rv?.addItemDecoration(dividerItemDecoration)


        items.add(Item("Rakesh"))
        items.add(Item("Niraj"))
        items.add(Item("Rekha"))
        items.add(Item("Anjali"))
        adapter!!.addAll(items)

    }

    override fun onClickAction(position: Int, item: List<Item>) {

        Log.e("position->"  +   position.toString(),
        "list size->"+item.size.toString())
    }
}