package com.fake.productslist.activities

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.fake.productslist.R
import com.fake.productslist.adapters.ProductListAdapter
import com.fake.productslist.model.Dataset
import com.fake.productslist.model.Product
import kotlinx.android.synthetic.main.a_current_product_list.*
import kotlinx.android.synthetic.main.content_current_product_list.*

class CurrentProductListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_current_product_list)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val pos = intent.getIntExtra("pos", 0)
        supportActionBar?.setTitle(Dataset.dataset[pos].name)
        val viewManager = LinearLayoutManager(this)
        val viewAdapter = ProductListAdapter(pos)
        prod_recycler.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        fab.setOnClickListener {
            dialog = AlertDialog.Builder(this)
                .setView(layoutInflater.inflate(R.layout.d_newproduct,null))
                .setPositiveButton("Добавить", { id, dialoga->
                    run {
                        var price = dialog?.findViewById<EditText>(R.id.product_price)?.text.toString()
                        var count = dialog?.findViewById<EditText>(R.id.product_count)?.text.toString()
                        (prod_recycler.adapter as ProductListAdapter).addItem(
                                Product(name = dialog?.findViewById<EditText>(R.id.product_name)?.text.toString(),
                                        comment = dialog?.findViewById<EditText>(R.id.product_comment)?.text.toString(),
                                        type_count = dialog?.findViewById<Spinner>(R.id.count_type)?.selectedItem.toString(),
                                        price = if(price!="")price.toDouble()else 0.0,
                                        count = if(count!="")count.toDouble()else 0.0,
                                        category = dialog?.findViewById<Spinner>(R.id.category)?.selectedItem.toString()))
                    }})
                .setNegativeButton("Отмена",{id,dialog->{}})
                .create()
        dialog?.show()
        val category=dialog?.findViewById<Spinner>(R.id.category)
        category?.setSelection((category.adapter as ArrayAdapter<String>).getPosition("Другое"))
        val count_type = dialog?.findViewById<Spinner>(R.id.count_type)
        count_type?.setSelection(count_type.firstVisiblePosition)
        }

    }
    var dialog: AlertDialog? = null


    override fun onResume(){
        super.onResume()
        prod_recycler.adapter?.notifyDataSetChanged()
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
