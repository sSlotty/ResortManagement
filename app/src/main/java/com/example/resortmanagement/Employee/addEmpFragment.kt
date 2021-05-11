package com.example.resortmanagement.Employee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import com.example.resortmanagement.Guest.addGuestFragment
import com.example.resortmanagement.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addEmpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addEmpFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        val v:View = inflater.inflate(R.layout.fragment_add_emp, container, false)
        val btnSave = v.findViewById<ImageButton>(R.id.btnSaveEmp)
        btnSave.setOnClickListener {
            Toast.makeText(this.context,"Test Save emp",Toast.LENGTH_LONG).show()
        }
        return v
    }

    companion object {

        fun newInstance(): addEmpFragment {
            return addEmpFragment()
        }
    }
}