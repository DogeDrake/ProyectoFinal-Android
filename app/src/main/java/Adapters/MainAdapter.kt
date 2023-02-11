package com.example.recycleviewdemo1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad3_a.R
import com.example.unidad3_a.UserRutinasResponse


class MainAdapter(
    private val mDataSet: List<UserRutinasResponse.Data>,
    val OnClick: (UserRutinasResponse.Data) -> Unit
) :
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
        fun bindItems(data: UserRutinasResponse.Data) {
            mytexto.text = data.attributes.titulorutina
            mydesc.text = data.id.toString()
        }
    }
}