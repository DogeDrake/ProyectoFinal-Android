package Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad3_a.R

class UserInfoAdapter(
    private var dataa: ArrayList<String>
) : RecyclerView.Adapter<UserInfoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val imgInfoIcon = holder.itemView.findViewById<ImageView>(R.id.imgInfo)
        val txtInfoTitle = holder.itemView.findViewById<TextView>(R.id.txtUserInfo)
        val tvInfoUser = holder.itemView.findViewById<TextView>(R.id.TVDatoUser)

        if (position == 0) {
            imgInfoIcon.setImageResource(R.drawable.ic_height)
            txtInfoTitle.text = "ALTURA"
            tvInfoUser.text = dataa[0]

        } else if (position == 1) {
            imgInfoIcon.setImageResource(R.drawable.ic_weight)
            txtInfoTitle.text = "PESO"
            tvInfoUser.text = dataa[1]

        } else if (position == 2) {
            imgInfoIcon.setImageResource(R.drawable.ic_calculator_imc)
            txtInfoTitle.text = "IMC"
            tvInfoUser.text = dataa[2]

        } else if (position == 3) {
            imgInfoIcon.setImageResource(R.drawable.ic_age)
            txtInfoTitle.text = "NACIMIENTO"
            tvInfoUser.text = dataa[3]

        }



    }

    override fun getItemCount(): Int = 4

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}




