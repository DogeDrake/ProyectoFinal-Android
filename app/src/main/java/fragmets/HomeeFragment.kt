package fragmets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad3_a.R

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.recycleviewdemo1.MainAdapter
import com.example.unidad3_a.ApiRest
import com.example.unidad3_a.RutinaPopulateResponse
import com.example.unidad3_a.UserRutinasResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeeFragment : Fragment() {

    private lateinit var adapter: MainAdapter
    var id_Usuario = ""
    val TAG = "MainActivity"
    var datos: ArrayList<RutinaPopulateResponse.Data> = ArrayList()
    val InfoRutinas = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUserRutinesPopualte()
        val id = arguments?.getString("idUsuario")
        Log.i("MainActivity", id.toString())
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Pagina Principal"

        adapter = MainAdapter(datos) { agent ->
            // var agentobj = it //llama al objeto que clickeas (item AgenteAdapter)
            activity?.let {
                val fragment = HomeDetailFragment()
                fragment.arguments = Bundle()
                fragment.arguments?.putSerializable("usuarios", agent)

                activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
                    ?.replace(R.id.container, fragment)?.commit()
            }
        }

        val mainRecyclerView = view?.findViewById<RecyclerView>(R.id.rvLista)

        mainRecyclerView?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mainRecyclerView?.adapter = adapter


    }

    private fun getUserRutinesPopualte() {
        val call = ApiRest.service.getUserRutinesPopualte()
        call.enqueue(object : Callback<RutinaPopulateResponse> {
            override fun onResponse(
                call: Call<RutinaPopulateResponse>,
                response: Response<RutinaPopulateResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    datos.clear()
                    datos.addAll(body.data)
                    Log.i(TAG, datos.toString())
                    for (a in datos) {
                        Log.i(TAG, "entroooo!!!!")
                        //InfoRutinas.add(a.attributes.titulorutina)
                        //InfoRutinas.add(a.attributes.publishedAt)
                    }
                    adapter?.notifyDataSetChanged()
                    Log.d(TAG, InfoRutinas.toString())
                    // Imprimir aqui el listado con logs
                } else {
                    Log.e(TAG, response.errorBody()?.string() ?: "Porto")
                }
            }

            override fun onFailure(
                call: Call<RutinaPopulateResponse>,
                t: Throwable
            ) {
                Log.e(TAG, t.message.toString())
            }
        })
    }
}

