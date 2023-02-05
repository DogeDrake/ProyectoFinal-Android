package com.example.recycleviewdemo1

import Adapters.user
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad3_a.R



class HomeDatosAdapter(private val mDataSet: List<user.Data.Ejercicios>, val OnClick: (user.Data.Ejercicios) -> Unit) :
    RecyclerView.Adapter<HomeDatosAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.ejercicios, parent, false)
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
        val mytexto = v.findViewById<TextView>(R.id.NameEjercicio)
        val mydesc = v.findViewById<TextView>(R.id.RepeticionesTV)
        fun bindItems(data: user.Data.Ejercicios) {
            mytexto.text = data.nombre
            mydesc.text = data.repeticiones
        }
    }
}