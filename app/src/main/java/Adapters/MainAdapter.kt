package com.example.recycleviewdemo1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad3_a.R
import com.example.unidad3_a.RutinaPopulateResponse
import com.example.unidad3_a.UserRutinasResponse


class MainAdapter(
    private var mDataSet: List<RutinaPopulateResponse.Data>,
    val OnClick: (RutinaPopulateResponse.Data) -> Unit
) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var mDataFiltered: MutableList<RutinaPopulateResponse.Data> = ArrayList(mDataSet)


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

    fun filter(text: String) {
        mDataFiltered.clear()
        if (text.isEmpty()) {
            mDataFiltered.addAll(mDataSet)
        } else {
            val search = text.lowercase()

            for (item in mDataSet) {
                if (item.attributes.titulorutina.lowercase().contains(search)) {
                    mDataFiltered.add(item)
                }
            }
        }

        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return mDataFiltered.size
    }

    inner class MainViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        val mytexto = v.findViewById<TextView>(R.id.tvTitulo)
        val mydesc = v.findViewById<TextView>(R.id.tvDescrp)
        fun bindItems(data: RutinaPopulateResponse.Data) {
            mytexto.text = data.attributes.titulorutina
            mydesc.text = data.id.toString()
        }
    }
}