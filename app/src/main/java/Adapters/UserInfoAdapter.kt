package Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad3_a.R
import com.example.unidad3_a.RutinaPopulateResponse
import com.example.unidad3_a.UserResponse
import kotlin.math.log

class UserInfoAdapter(
    private var dataa: ArrayList<UserResponse.Data>, val OnClick: (UserResponse.Data) -> Unit
) : RecyclerView.Adapter<UserInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val imgInfoIcon = holder.itemView.findViewById<ImageView>(R.id.imgInfo)
        val txtInfoTitle = holder.itemView.findViewById<TextView>(R.id.txtUserInfo)
        Log.i("posicion", getItemCount().toString())
        var i = 0
        while (i < 3) {
            if (position == 0) {
                imgInfoIcon.setImageResource(R.drawable.ic_height)
                txtInfoTitle.text = "ALTURA"


            } else if (position == 1) {
                imgInfoIcon.setImageResource(R.drawable.ic_weight)
                txtInfoTitle.text = "PESO"


            } else if (position == 2) {
                imgInfoIcon.setImageResource(R.drawable.ic_calculator_imc)
                txtInfoTitle.text = "IMC"


            } else if (position == 3) {
                imgInfoIcon.setImageResource(R.drawable.ic_age)
                txtInfoTitle.text = "NACIMIENTO"


            }
            i = i + 1
            Log.i("posicion", i.toString())
        }

        val data = dataa[position]
        holder.bindItems(data)

        holder.itemView.setOnClickListener {
            OnClick(data)
        }
    }

    override fun getItemCount(): Int = dataa.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mytexto = itemView.findViewById<TextView>(R.id.TVDatoUser)
        fun bindItems(data: UserResponse.Data) {
            when (bindingAdapterPosition) {
                0 -> mytexto.text = data.attributes.altura.toString()
                1 -> mytexto.text = data.attributes.peso.toString()
                2 -> mytexto.text = data.attributes.imc.toString()
                3 -> mytexto.text = data.attributes.nacimiento
            }
        }
    }
}




