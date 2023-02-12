package fragmets

import Adapters.ListaEjerciciosAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewdemo1.MainAdapter
import com.example.unidad3_a.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//DEEP3
class AddEjerciciosFragment : Fragment() {

    val listaDatos = ArrayList<DatosCompuestos>()
    var numeroRep: String = "10"
    var nombreEj: String = ""


    private lateinit var adapter: ListaEjerciciosAdapter
    val TAG = "MainActivity"
    var datos: ArrayList<EjerciciosResponse.Data> = ArrayList()
    val InfoRutinas = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_ejercicios, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getEjercicios()
        var startPoint = 0
        var endPoint = 0
        super.onViewCreated(view, savedInstanceState)
        var RVEjerciciois = view.findViewById<RecyclerView>(R.id.RVlistaEjercicios)

        adapter =
            ListaEjerciciosAdapter(datos) {
                //Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                nombreEj = datos.toString()
                //Log.v("miapp", it)
            }
        RVEjerciciois.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        RVEjerciciois.adapter = adapter



        var barras = view.findViewById<SeekBar>(R.id.NumeroRepeticionesSeekBar)
        var numResp = view.findViewById<TextView>(R.id.TVRepeticioines)

        barras.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                numResp.text = progress.toString()
                numeroRep = numResp.text as String

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    startPoint = seekBar.progress
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    endPoint = seekBar.progress
                }
            }
        })


        var ButtonADDEjercicios = view.findViewById<Button>(R.id.AñadirEjercicioARutina)
        ButtonADDEjercicios.setOnClickListener {
            if (!nombreEj.equals("")) {
                val dato = DatosCompuestos(nombreEj, numeroRep)
                listaDatos.add(dato)
                val fragment = AddRunineFragment()
                fragment.arguments = Bundle()
                fragment.arguments?.putSerializable("dato", listaDatos)

                activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
                    ?.replace(R.id.container, fragment)?.commit()

                Log.v("listadatos", listaDatos.toString())
                /*
                activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
                    ?.replace(R.id.container, AddRunineFragment())?.commit()

                 */
            }
        }
    }

    private fun getEjercicios() {
        val call = ApiRest.service.getEjercicios()
        call.enqueue(object : Callback<EjerciciosResponse> {
            override fun onResponse(
                call: Call<EjerciciosResponse>,
                response: Response<EjerciciosResponse>
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
                call: Call<EjerciciosResponse>,
                t: Throwable
            ) {
                Log.e(TAG, t.message.toString())
            }
        })
    }


}

