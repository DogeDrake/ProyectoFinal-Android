package com.example.recycleviewdemo1

import Adapters.user
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad3_a.R



class MainAdapter(private val mDataSet: List<user.Data>, val OnClick: (user.Data) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false)
        return MainViewHolder(v)
    }



    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = mDataSet.get(position)
        holder.bindItems(data)
        holder.itemView.setOnClickListener {
            OnClick(data)
        }
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    inner class MainViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        val mytexto = v.findViewById<TextView>(R.id.tvTitulo)
        val mydesc = v.findViewById<TextView>(R.id.tvDescrp)
        fun bindItems(data: user.Data) {
            mytexto.text = data.name
            mydesc.text = data.surname
        }
    }
}