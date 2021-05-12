package com.example.resortmanagement.Employee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.resortmanagement.Guest.addGuestFragment
import com.example.resortmanagement.MainViewModel
import com.example.resortmanagement.R
import kotlinx.android.synthetic.main.fragment_add_emp.*
import kotlinx.android.synthetic.main.fragment_add_guest.*
import org.koin.androidx.viewmodel.ext.android.viewModel

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

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        val v:View = inflater.inflate(R.layout.fragment_add_emp, container, false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSaveEmp.setOnClickListener {
            if (inputEmpUsername.text.isNullOrEmpty() or inputEmpPassword.text.isNullOrEmpty() or inputEmpName.text.isNullOrEmpty() or inputEmpTel.text.isNullOrEmpty()){
                Toast.makeText(this.context,"Input is null",Toast.LENGTH_LONG).show()
            }else{
                val emp = hashMapOf<String,Any>("username" to inputEmpUsername?.text.toString(),"password" to inputEmpPassword?.text.toString(),"name" to inputEmpName?.text.toString(),"tel" to inputEmpTel?.text.toString())
                addEmp(emp)
            }
        }
    }

    private fun addEmp(emp:HashMap<String,Any>){
        val load =  SweetAlertDialog(this.context, SweetAlertDialog.PROGRESS_TYPE)
            .showCancelButton(false)
            .setTitleText("loading...");

        val success = SweetAlertDialog(this.context, SweetAlertDialog.SUCCESS_TYPE)
            .showCancelButton(false)

        load.show()


        viewModel.addEmp(emp){ status: Boolean, text: String ->
            if (status){
                load.hide()
                success.titleText = text
                success.show()
            }else{
                load.hide()
            }
        }
    }

    companion object {

        fun newInstance(): addEmpFragment {
            return addEmpFragment()
        }
    }
}