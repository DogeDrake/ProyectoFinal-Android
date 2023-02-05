package fragmets



import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.unidad3_a.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE
import com.google.android.material.textfield.TextInputLayout.END_ICON_PASSWORD_TOGGLE
import java.util.regex.Pattern

class RegistroFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.isVisible = false
        view?.findViewById<Button>(R.id.btHome)?.setOnClickListener {
            view.findViewById<TextView>(R.id.txtErrorRegistro).text = ""
            val nombre = view.findViewById<EditText>(R.id.txtNombre).text.toString()
            val apellido = view.findViewById<EditText>(R.id.txtApellido).text.toString()
            val fecha = view.findViewById<EditText>(R.id.etDate).text.toString()
            val correo = view.findViewById<EditText>(R.id.txtMail).text.toString()
            val contraseña = (view.findViewById<EditText>(R.id.txtPassword).text.toString())
            view.findViewById<TextInputLayout>(R.id.edtPassErr).setEndIconMode(END_ICON_PASSWORD_TOGGLE)
            if (nombre == "" || apellido == "" || fecha == "" || correo == "" || contraseña == ""
            ) {
                view.findViewById<TextInputLayout>(R.id.edtPassErr).setEndIconMode(END_ICON_PASSWORD_TOGGLE)
                view.findViewById<TextView>(R.id.txtErrorRegistro).text = " PORFAVOR RELLENE TODOS LOS CAMPOS"
            }else if(!isPasswordValid(contraseña)&&!isEmailValid(correo)){
                view.findViewById<TextInputLayout>(R.id.edtPassErr).setEndIconMode(END_ICON_NONE)
                view.findViewById<TextView>(R.id.txtMail).error= "Formato de correo incorrecto"
                view.findViewById<TextView>(R.id.txtPassword).error= "Contraseña debe tener al menos una mayuscula, una minuscula y un caracter especial"
            }
            else if (!isPasswordValid(contraseña)){
                view.findViewById<TextInputLayout>(R.id.edtPassErr).setEndIconMode(END_ICON_NONE)
                view.findViewById<TextView>(R.id.txtPassword).error= "Contraseña debe tener al menos 7 caracteres, una mayuscula, una minuscula y un caracter especial"
            }else if (!isEmailValid(correo)){
                view.findViewById<TextView>(R.id.txtMail).error= "Formato de correo incorrecto"

            }
            else {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.bottomNavigationView, HomeeFragment())?.commit()
            }
        }
        view.findViewById<EditText>(R.id.etDate).setOnClickListener() {
            showDatePickerDialog()
        }
    }
    fun isEmailValid(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
    fun isPasswordValid(password: String): Boolean {
        // Patrón para al menos una letra mayúscula, una minúscula y un caracter especial
        val pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&+=])(?=\\S+\$).{7,}$"
        val compiledPattern = Pattern.compile(pattern)
        val matcher = compiledPattern.matcher(password)
        return matcher.matches()
    }
    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        getActivity()?.let { datePicker.show(it.getSupportFragmentManager(), "datePicker") }
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        view?.findViewById<EditText>(R.id.etDate)
            ?.setText("$day/$month/$year")
    }
    override fun onStop() {
        super.onStop()
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.isVisible = true

    }
}