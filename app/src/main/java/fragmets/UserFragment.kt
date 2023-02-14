package fragmets

import Adapters.UserInfoAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad3_a.ApiRest
import com.example.unidad3_a.R
import com.example.unidad3_a.UserResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserFragment : Fragment() {
    private lateinit var adapter: UserInfoAdapter
    var datos: ArrayList<String> = arrayListOf("-", "-", "-", "-", "-")
    var value: String? = "-1"

    //  var username: String = ""
    val TAG = "MainActivity"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.topbarprofile, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_set -> set()
            R.id.action_add -> add()
            R.id.DeleteAcc -> delete()
            R.id.logout -> logout()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun add() {
        Log.d(tag, "Add Clicked")
        Toast.makeText(context, "Mas", Toast.LENGTH_SHORT).show()
    }

    private fun set() {
        Log.d(tag, "Settings Clicked")
        Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, UserEditFragment())?.addToBackStack(null)?.commit()
    }

    private fun delete() {
        lifecycleScope.launch {
            try {
                val response = ApiRest.service.deleteUser(value!!)
                if (response.isSuccessful) {
                    Log.d(tag, "Delete Successful")
                    Toast.makeText(context, "Cuenta Eliminada", Toast.LENGTH_SHORT).show()
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.container, LoginFragment())?.commit()
                } else {
                    Log.d(tag, "Delete Failed")
                }
            } catch (e: Exception) {
                Log.e(tag, "Error deleting user", e)
            }
        }
        Log.d(tag, "Delete Clicked")
        Toast.makeText(context, "Cuenta Eliminada", Toast.LENGTH_SHORT).show()
    }

    private fun logout() {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, LoginFragment())?.commit()
        Log.d(tag, "Log out Clicked")
        Toast.makeText(context, "Cuenta Cerrada", Toast.LENGTH_SHORT).show()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Copiar esto para recibir id del usuario desde cualquier fragment
        val sharedPreferences = context?.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        value = sharedPreferences?.getString("user", "-1")

        getUser(value!!)
        setHasOptionsMenu(true)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Perfil De Usuario"


        adapter = UserInfoAdapter(datos)


        var rvUserInfo = view.findViewById<RecyclerView>(R.id.rvUsersInfo)
        rvUserInfo.layoutManager = GridLayoutManager(context, 2)
        rvUserInfo.adapter = adapter

        var tvUsername = view.findViewById<TextView>(R.id.textView)
        // tvUsername.text = username

    }

    private fun getUser(id: String) {

        val call = ApiRest.service.getUser(id)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    datos.clear()
                    datos.add(body.data[0].attributes.altura.toString())
                    datos.add(body.data[0].attributes.peso.toString())
                    datos.add(body.data[0].attributes.imc.toString())
                    datos.add(body.data[0].attributes.nacimiento)


                    Log.i(TAG, datos.toString())
                    for (a in datos) {
                        Log.i(TAG, "entroooo!!!!$a")

                    }
                    adapter?.notifyDataSetChanged()
                    // Imprimir aqui el listado con logs

                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {

            }
        })

    }
}