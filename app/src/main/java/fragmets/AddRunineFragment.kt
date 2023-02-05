package fragmets

import Adapters.CrearRutinaAdapter
import Adapters.user
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad3_a.DatosCompuestos
import com.example.unidad3_a.R

//DEEP2
class AddRunineFragment : Fragment() {

    var listaDatos = ArrayList<DatosCompuestos>()
    private lateinit var adapter2: CrearRutinaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_runine, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val datosllegados =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                arguments?.getSerializable(
                    "dato" //Key del Serializable de la clase
                    //DatosCompuestos::class.java
                ) as? ArrayList<DatosCompuestos>
            } else {
                arguments?.getSerializable("dato") as? ArrayList<DatosCompuestos>
            }

/*
        //listaDatos.add(datosllegados)

            val nombre = datosllegados?.string1
            val numero = datosllegados?.string2
        if(nombre != null) {
            val datosaurio = DatosCompuestos(nombre!!, numero!!)
            listaDatos.add(datosaurio)
        }


            listaDatos.forEach {
                Log.d("Lista", listaDatos.size.toString())
            }

 */

        if (datosllegados != null) {
            adapter2 = CrearRutinaAdapter(datosllegados)
            val mainRecyclerView2 = view?.findViewById<RecyclerView>(R.id.RVEjerciciosEnRutinas)
            mainRecyclerView2?.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            mainRecyclerView2?.adapter = adapter2
        } else {
            Log.i("nullardo", "nullardopaco")
        }

        var ButtonAñadirEjercicio = view.findViewById<Button>(R.id.añadirEjercicio)
        var ButtonGuardarRutina = view.findViewById<Button>(R.id.GuardarRutina)

        ButtonAñadirEjercicio.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
                ?.replace(R.id.container, AddEjerciciosFragment())?.commit()
        }
        ButtonGuardarRutina.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)
                ?.replace(R.id.container, AddFragment())?.commit()


        }


    }

}