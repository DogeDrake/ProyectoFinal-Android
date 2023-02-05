package fragmets

import Adapters.UserInfoAdapter
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unidad3_a.R


class UserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
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
    }

    private fun delete() {
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

        var rvUserInfo = view.findViewById<RecyclerView>(R.id.rvUsersInfo)
        rvUserInfo.layoutManager = GridLayoutManager(context, 2)
        rvUserInfo.adapter = UserInfoAdapter()

    }
}