package com.fake.productslist.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.fake.productslist.R
import com.fake.productslist.activities.CurrentProductListActivity
import com.fake.productslist.model.Dataset
import com.fake.productslist.model.Product
import kotlinx.android.synthetic.main.product_card.view.*

class ProductListAdapter(val buyListPosition: Int) : RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.product_card, parent, false) as CardView
        return MyViewHolder(cardView, buyListPosition)
    }

    fun addItem(product: Product) {
        (Dataset.dataset[buyListPosition].products as ArrayList<Product>).add(product)
        notifyItemInserted(Dataset.dataset[buyListPosition].products.size - 1)
    }


    override fun getItemCount(): Int {
        return Dataset.dataset[buyListPosition].products.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.prodCat.text = Dataset.dataset[buyListPosition].products[position].category
        holder.prodCount.text = "${Dataset.dataset[buyListPosition].products[position].count}"
        holder.prodName.text = Dataset.dataset[buyListPosition].products[position].name
        holder.prodType.text = Dataset.dataset[buyListPosition].products[position].type_count
        holder.prodPrice.text = "Цена:${Dataset.dataset[buyListPosition].products[position].price}"
    }


    class MyViewHolder(val cardView: CardView, val pos: Int) : RecyclerView.ViewHolder(cardView), View.OnClickListener {
        init {
            cardView.setOnClickListener(this)
        }
        override fun onClick(view: View?) {
            dialoga = AlertDialog.Builder(view?.context)
                    .setView(LayoutInflater.from(view?.context).inflate(R.layout.d_newproduct, null))
                    .setPositiveButton("Сохранить", DialogInterface.OnClickListener { dialog, id ->
                        (Dataset.dataset[pos].products as ArrayList<Product>).set(adapterPosition,
                                Product(name = dialoga?.findViewById<EditText>(R.id.product_name)?.text.toString(),
                                comment = dialoga?.findViewById<EditText>(R.id.product_comment)?.text.toString(),
                                type_count = dialoga?.findViewById<Spinner>(R.id.count_type)?.selectedItem.toString(),
                                price = dialoga?.findViewById<EditText>(R.id.product_price)?.text.toString().toDouble(),
                                count = dialoga?.findViewById<EditText>(R.id.product_count)?.text.toString().toDouble(),
                                category = dialoga?.findViewById<Spinner>(R.id.category)?.selectedItem.toString()))
                        val intent = Intent(cardView.context,CurrentProductListActivity::class.java)
                        intent.putExtra("pos",pos)
                        cardView.context.startActivity(intent)
                        //TODO:это какое-то гавно, надо переделать, но пока и так сойдет
                    })
                    .setNegativeButton("Отмена", DialogInterface.OnClickListener { dialog, id -> })
                    .create()
            dialoga?.show()
            val name = dialoga?.findViewById<EditText>(R.id.product_name)
            name?.setText(Dataset.dataset[pos].products[adapterPosition].name)
            val count = dialoga?.findViewById<EditText>(R.id.product_count)
            count?.setText(Dataset.dataset[pos].products[adapterPosition].count.toString())
            val price = dialoga?.findViewById<EditText>(R.id.product_price)
            price?.setText(Dataset.dataset[pos].products[adapterPosition].price.toString())
            val comment = dialoga?.findViewById<EditText>(R.id.product_comment)
            comment?.setText(Dataset.dataset[pos].products[adapterPosition].comment)
            val category = dialoga?.findViewById<Spinner>(R.id.category)
            category?.setSelection((category.adapter as ArrayAdapter<String>).getPosition(Dataset.dataset[pos].products[adapterPosition].category))
            val type = dialoga?.findViewById<Spinner>(R.id.count_type)
            type?.setSelection((type.adapter as ArrayAdapter<String>).getPosition(Dataset.dataset[pos].products[adapterPosition].type_count))
        }

        var dialoga: AlertDialog? = null

        val prodName = cardView.prod_name
        val prodCount = cardView.prod_count
        val prodCat = cardView.prod_category
        val prodType = cardView.prod_type
        val prodPrice = cardView.prod_price
    }

}


