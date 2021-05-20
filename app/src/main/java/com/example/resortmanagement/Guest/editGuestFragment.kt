package com.example.resortmanagement.Guest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.resortmanagement.MainViewModel
import com.example.resortmanagement.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_edit_guest.*
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [editGuestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class editGuestFragment : Fragment() {

    lateinit var id: String
    private val viewModel by viewModel<MainViewModel>()

    companion object {
        fun newInstance(idPass: String) = editGuestFragment().apply {
            arguments = Bundle().apply {
                id = idPass
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSaveRoom?.setOnClickListener {
            val update = hashMapOf<String,Any>("guestID" to roomId?.text.toString(),"name" to roomType?.text.toString(),"tel" to roomPerson?.text.toString())
            updateGuest(update)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View =inflater.inflate(R.layout.fragment_edit_guest, container, false)
        val values = hashMapOf<String,Any>("guestID" to this.id)

        val load =  SweetAlertDialog(this.context, SweetAlertDialog.PROGRESS_TYPE)
            .showCancelButton(false)
            .setTitleText("loading...");
        load.show()

        val onError = SweetAlertDialog(this.context,SweetAlertDialog.ERROR_TYPE)
            .showCancelButton(false)

        viewModel.getGuestByID(values){status->
            if(status){
                load.hide()
                val json = Gson().toJson(viewModel.allGuest.value)
                val data = viewModel.allGuest.value
                var name =  data?.get(0)?.name.toString()
                var tel = data?.get(0)?.tel.toString()
                roomId.text = data?.get(0)?._id.toString()
                roomType.setText(name)
                roomPerson.setText(tel)
//                Toast.makeText(this.context,json,Toast.LENGTH_LONG).show()
            }else{
                load.hide()
                onError.titleText = "Sorry , userID incorrect or Network connection lost."
                onError.show()
            }
        }

        return v
    }


    private fun updateGuest(guest:HashMap<String,Any>){
        val load =  SweetAlertDialog(this.context, SweetAlertDialog.PROGRESS_TYPE)
            .showCancelButton(false)
            .setTitleText("loading...");

        val success = SweetAlertDialog(this.context,SweetAlertDialog.SUCCESS_TYPE)
            .showCancelButton(false)

        load.show()

        viewModel.updateGuest(guest){ status->
            if (status){
                load.hide()
                success.titleText = "Success to update"
                success.show()
            }else{
                load.hide()
            }
        }
    }

}