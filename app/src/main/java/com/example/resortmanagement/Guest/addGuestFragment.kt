package com.example.resortmanagement.Guest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.resortmanagement.MainViewModel
import com.example.resortmanagement.R
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addGuestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addGuestFragment : Fragment() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View = inflater.inflate(R.layout.fragment_add_guest, container, false)
        var id = v.findViewById<EditText>(R.id.inputID)
        var name = v.findViewById<EditText>(R.id.inputName)
        var tel = v.findViewById<EditText>(R.id.inputPhone)
        val btnSave = v.findViewById<ImageButton>(R.id.btnSaveGuest)

        btnSave.setOnClickListener {
            if (id.text.isNullOrEmpty() or name.text.isNullOrEmpty() or tel.text.isNullOrEmpty()){
                Toast.makeText(this.context,"Input is null",Toast.LENGTH_LONG).show()
            }else{
                val values = hashMapOf<String, Any>("id" to id, "name" to name,"tel" to tel)
                Log.i("Value", name.toString())
            }
        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    companion object {

        fun newInstance(): addGuestFragment {
            return addGuestFragment()
        }
    }
}