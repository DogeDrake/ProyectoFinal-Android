package Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad3_a.DatosCompuestos
import com.example.unidad3_a.R

class CrearRutinaAdapter(
    private val mDataSet: List<DatosCompuestos>
) :
    RecyclerView.Adapter<CrearRutinaAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.ejerciciosyrepeticiones, parent, false)
        return MainViewHolder(v)
    }


    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val data = mDataSet.get(position)
        holder.bindItems(data)
        /*
        holder.itemView.setOnClickListener {
        }

         */
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

    inner class MainViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        val txtRep = v.findViewById<TextView>(R.id.ejercicioRepeticiones)
        val numRep = v.findViewById<TextView>(R.id.numeroRepeticiones)
        fun bindItems(data: DatosCompuestos) {
            txtRep.text = data.string1
            numRep.text = data.string2
        }
    }
}