package fragmets

import Adapters.user
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewdemo1.HomeDatosAdapter
import com.example.recycleviewdemo1.MainAdapter
import com.example.unidad3_a.R

private lateinit var adapter2: HomeDatosAdapter

class HomeDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val usuario =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                arguments?.getSerializable(
                    "usuarios", //Key del Serializable de la clase AgentFragment
                    user.Data::class.java
                ) as? user.Data
            } else {
                arguments?.getSerializable("usuarios") as? user.Data
            }

        adapter2 = HomeDatosAdapter(usuario!!.exer) {
        }

        val mainRecyclerView2 = view?.findViewById<RecyclerView>(R.id.recogidaDatos)
        mainRecyclerView2?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        mainRecyclerView2?.adapter = adapter2

        (activity as? AppCompatActivity)?.supportActionBar?.title = usuario?.name
    }
}