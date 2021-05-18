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
import kotlinx.android.synthetic.main.fragment_add_guest.btnSaveRoom
import kotlinx.android.synthetic.main.fragment_add_guest.roomId
import kotlinx.android.synthetic.main.fragment_add_guest.roomType
import kotlinx.android.synthetic.main.fragment_add_guest.roomPerson
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



        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSaveRoom.setOnClickListener {
            if (roomId.text.isNullOrEmpty() or roomType.text.isNullOrEmpty() or roomPerson.text.isNullOrEmpty()){
                Toast.makeText(this.context,"Input is null",Toast.LENGTH_LONG).show()
            }else{
                val update = hashMapOf<String,Any>("guestID" to roomId?.text.toString(),"name" to roomType?.text.toString(),"tel" to roomPerson?.text.toString())
                addGuest(update)
            }
        }

    }

    private fun addGuest(guest:HashMap<String,Any>){
        val load =  SweetAlertDialog(this.context, SweetAlertDialog.PROGRESS_TYPE)
            .showCancelButton(false)
            .setTitleText("loading...");

        val success = SweetAlertDialog(this.context, SweetAlertDialog.SUCCESS_TYPE)
            .showCancelButton(false)

        val error = SweetAlertDialog(this.context, SweetAlertDialog.ERROR_TYPE)
            .showCancelButton(false)

        load.show()


        viewModel.addGuest(guest){ status: Boolean ->
            if (status){
                load.hide()
                success.titleText = "success to add Guest"
                success.show()
            }else{
                load.hide()
                error.titleText = "Already have Guest ID"
                error.show()
            }
        }
    }
    companion object {

        fun newInstance(): addGuestFragment {
            return addGuestFragment()
        }
    }
}