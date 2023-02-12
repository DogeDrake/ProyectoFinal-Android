package Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad3_a.EjerciciosResponse
import com.example.unidad3_a.R

class ListaEjerciciosAdapter(
    private val DefaultData: List<EjerciciosResponse.Data>,//Cambiar
    var function: (EjerciciosResponse.Data) -> Unit
) :
    RecyclerView.Adapter<ListaEjerciciosAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.ejercicioslista, parent, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = DefaultData.get(position)
        data.let {
            holder.bindItems(data)
        }
        holder.itemView.setOnClickListener {
            function(data)
        }
    }

    override fun getItemCount(): Int {
        return DefaultData.size
    }

    inner class MainViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        var TextoEjercicios = v.findViewById<TextView>(R.id.EjerciciosSeleccionables)

        fun bindItems(data: EjerciciosResponse.Data) {
            TextoEjercicios.text = data.attributes.ejercicionombre
        }
    }
}