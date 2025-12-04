package com.example.retrofit.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.model.CryptoModel

class RecyclerViewAdapter(private val cryptoList: ArrayList<CryptoModel>, private val listener : Listener) : RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    interface Listener{
        fun onItemClick(cryptoModel: CryptoModel)
    }

     val colors : Array<String> = arrayOf("#7E57C2", "#42A5F5")


    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): RowHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return RowHolder(view)



    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        holder.bind(cryptoList[position],colors,position,listener)


    }

    override fun getItemCount(): Int {

        return cryptoList.count()

    }

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val nameText = view.findViewById<TextView>(R.id.text_name)
        private val priceText = view.findViewById<TextView>(R.id.text_price)

        fun bind(cryptoModel: CryptoModel, colors: Array<String>, position: Int, listener: Listener){
            itemView.setOnClickListener {
                listener.onItemClick(cryptoModel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 2]))
            nameText.text = cryptoModel.name
            priceText.text = cryptoModel.quote.usd.price.toString()

        }

    }


}