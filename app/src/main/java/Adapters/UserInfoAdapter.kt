package Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad3_a.R
import com.example.unidad3_a.RutinaPopulateResponse
import com.example.unidad3_a.UserResponse

class UserInfoAdapter(
    private val data: ArrayList<UserResponse.Data>, val OnClick: (UserResponse.Data) -> Unit
) : RecyclerView.Adapter<UserInfoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imgInfoIcon = holder.itemView.findViewById<ImageView>(R.id.imgInfo)
        val txtInfoTitle = holder.itemView.findViewById<TextView>(R.id.txtUserInfo)

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
            txtInfoTitle.text = "EDAD"
        }

        val userData = data[position]
        userData.let {
            holder.bindItems(userData)
        }

        holder.itemView.setOnClickListener {
            OnClick(userData)
        }
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mytexto = itemView.findViewById<TextView>(R.id.TVDatoUser)
        fun bindItems(userData: UserResponse.Data) {
            mytexto.text = userData.attributes.altura.toString() + " cm"
        }
    }
}




