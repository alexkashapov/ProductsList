package com.fake.productslist.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.fake.productslist.adapters.BuyListAdapter
import com.fake.productslist.activities.CurrentProductListActivity
import com.fake.productslist.model.Dataset
import com.fake.productslist.R
import kotlinx.android.synthetic.main.f_main.view.*
import java.text.SimpleDateFormat
import java.util.*


class MainListFragment: Fragment(){

    lateinit var editText: EditText
    val date = Date()
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.US).format(date)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.f_main,container,false)
        val viewManager = LinearLayoutManager(view.context)
        val viewAdapter = BuyListAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler)
                .apply {
                    layoutManager=viewManager
                    adapter = viewAdapter
                }
        view.fab.setOnClickListener{
            val builder = AlertDialog.Builder(this.context)
                .setView(layoutInflater.inflate(R.layout.d_newlist,null))
                .setTitle("Новый список")
                .setPositiveButton(
                        "ok", DialogInterface.OnClickListener { dialog, id ->
                    val intent = Intent(this.context, CurrentProductListActivity::class.java)
                    (recyclerView.adapter as BuyListAdapter).addBuyList(editText.text.toString())
                    intent.putExtra("pos", Dataset.dataset.size-1)
//                    intent.putExtra("name", editText.text)
                    startActivity(intent)
                    // User clicked OK button
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id -> })
                .create()
        builder.show()
        editText = builder.findViewById<EditText>(R.id.new_list_name)!!
        editText.setText("Список $format")
        }
        return view
    }


}