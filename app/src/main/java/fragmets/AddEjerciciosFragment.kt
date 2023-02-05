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
import com.example.unidad3_a.DatosCompuestos
import com.example.unidad3_a.R

//DEEP3
class AddEjerciciosFragment : Fragment() {

    val listaDatos = ArrayList<DatosCompuestos>()
    var numeroRep: String = "10"
    var nombreEj: String = ""


    var demoNames = listOf(
        "Jamey Bush",
        "Casandra Red",
        "Melvin Detrick",
        "Mirella Jiggetts",
        "Brook Hetzel",
        "Eva Mccrystal",
        "Glennie Hiott",
        "Alverta Ruggles",
        "Floria Pedroza",
        "Marianela Redman",
        "Colby Bellew",
        "Marquerite Kite",
        "Marcelene Rhoads",
        "Taneka Burgin",
        "Marci Smits",
        "Michelle Madero",
        "Pinkie Josey",
        "Marlys Nieman",
        "Ling Reddick"
    )

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

        var startPoint = 0
        var endPoint = 0
        super.onViewCreated(view, savedInstanceState)
        var RVEjerciciois = view.findViewById<RecyclerView>(R.id.RVlistaEjercicios)
        val mAdapter =
            ListaEjerciciosAdapter(demoNames) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                nombreEj = it
                Log.v("miapp", it)
            }
        RVEjerciciois.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        RVEjerciciois.adapter = mAdapter

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


}

