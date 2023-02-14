package fragmets

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.unidad3_a.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {
    val TAG = "MainActivity"
    var datos: ArrayList<UserResponse.Data> = ArrayList()
    val username = mutableListOf<String>()
    var idUser = ""
    lateinit var correo:String
    lateinit var contasena:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        ApiRest.initService()
        getUsers()
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.isVisible = false
        view.findViewById<Button>(R.id.btIrHome).setOnClickListener {
             correo = view.findViewById<EditText>(R.id.txtCorreoLogIn).text.toString()
             contasena = view.findViewById<EditText>(R.id.txtContraseñaLogIn).text.toString()
            if (correo == "" || (contasena) == "") {
                view.findViewById<TextView>(R.id.txtError).text = "RELLENE TODOS LOS CAMPOS"
            } else {
                if (correo in username) {
                    val indexUser = username.indexOf(correo)
                    Log.d(TAG, username[indexUser + 1])
                    if ((contasena).equals(username[indexUser + 1])) {
                        //loginUser()
                        idUser = username[indexUser + 2]
                        val sharedPreferences =
                            context?.getSharedPreferences("prefs", Context.MODE_PRIVATE)
                        sharedPreferences!!.edit().putString("user", idUser).apply()
                        activity?.let {
                            val fragment = HomeeFragment()
                            Log.d(TAG, idUser)
                            fragment.arguments = Bundle().apply {
                                putString("idUsuario", idUser)
                            }
                            it?.supportFragmentManager?.beginTransaction()
                                ?.replace(R.id.container, fragment)?.commit()
                            (activity as? AppCompatActivity)?.supportActionBar?.show()
                        }

                    } else {
                        view.findViewById<TextView>(R.id.txtError).text = "CONTRASEÑA INCORRECTA"
                    }
                } else {
                    view.findViewById<TextView>(R.id.txtError).text = "CORREO INEXISTENTE"
                }
            }
        }
        view.findViewById<Button>(R.id.btIrRegistro).setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, RegistroFragment())?.addToBackStack(null)?.commit()

        }


    }

    override fun onStop() {
        super.onStop()
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.isVisible = true

    }

    private fun getUsers() {

        val call = ApiRest.service.getUsers()
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    datos.clear()
                    datos.addAll(body.data)

                    for (a in datos) {
                        Log.i(TAG, "entroooo!!!!")
                        username.add(a.attributes.email)
                        username.add(a.attributes.contrasena)
                        username.add(a.id.toString())
                    }

                    Log.d(TAG, username.toString())
                    // Imprimir aqui el listado con logs
                } else {
                    Log.e(TAG, response.errorBody()?.string() ?: "Porto")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())
            }
        })

    }
    private fun loginUser(){
        val crearUser = ApiService.DatosLogin(correo, contasena)
        val call = ApiRest.service.loginUser(crearUser)
        call.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                // maneja la respuesta exitosa aquí
                val body = response.body()
                if (response.isSuccessful && body != null){
                    var loginResponse = response.body()
                    print(loginResponse)
                }else{

                    Log.e(TAG, response.errorBody()?.toString()?: "Error")
                    view?.findViewById<TextView>(R.id.txtMail)?.error = response.errorBody()?.toString()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}