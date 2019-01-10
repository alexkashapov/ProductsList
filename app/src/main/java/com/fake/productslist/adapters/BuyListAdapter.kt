package com.fake.productslist.adapters

import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fake.productslist.activities.CurrentProductListActivity
import com.fake.productslist.R
import com.fake.productslist.model.BuyList
import com.fake.productslist.model.Dataset.Companion.dataset
import com.fake.productslist.model.Product
import kotlinx.android.synthetic.main.card_with_text.view.*
import java.util.*

class BuyListAdapter():RecyclerView.Adapter<BuyListAdapter.MyViewHolder>(){
    class MyViewHolder(val cardView: CardView):RecyclerView.ViewHolder(cardView), View.OnClickListener{
        override fun onClick(view: View?) {
            val intent = Intent(view?.context, CurrentProductListActivity::class.java)
            intent.putExtra("pos",adapterPosition)
            view?.context?.startActivity(intent)
        }

        init {
            cardView.setOnClickListener(this)
        }

        val textView = cardView.text


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_with_text,parent,false) as CardView
        return MyViewHolder(cardView)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = dataset[position].name
    }

    fun addBuyList(name: String){
        dataset.add(BuyList(name, Date(), ArrayList<Product>()))
        notifyDataSetChanged()
    }

    fun removeBuyList(position:Int){
        dataset.removeAt(position)
        notifyDataSetChanged()
    }

}