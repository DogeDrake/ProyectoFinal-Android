package fragmets

import Adapters.UserEditAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad3_a.ApiRest
import com.example.unidad3_a.R
import kotlinx.coroutines.launch


class UserEditFragment : Fragment() {
    var value: String? = "-1"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_edit, container, false)
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
    }

    private fun delete() {

        lifecycleScope.launch {
            try {
                val response = ApiRest.service.deleteUser(value!!)
                if (response.isSuccessful) {
                    Log.d(tag, "Delete Successful")
                    Toast.makeText(context, "Cuenta Eliminada", Toast.LENGTH_SHORT).show()
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
        Log.d(tag, "Edit Clicked")
        Toast.makeText(context, "Cuenta Cerrada", Toast.LENGTH_SHORT).show()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Perfil De Usuario"

        val sharedPreferences = context?.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        value = sharedPreferences?.getString("user", "-1")
        Log.i("IDUSER", value.toString())

        var rvUserInfo = view.findViewById<RecyclerView>(R.id.rvUsersInfo)
        rvUserInfo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvUserInfo.adapter = UserEditAdapter()


    }

}