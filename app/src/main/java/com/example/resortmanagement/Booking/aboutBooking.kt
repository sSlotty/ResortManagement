package com.example.resortmanagement.Booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.resortmanagement.MainViewModel
import com.example.resortmanagement.R
import kotlinx.android.synthetic.main.fragment_about_booking.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [aboutBooking.newInstance] factory method to
 * create an instance of this fragment.
 */
class aboutBooking : Fragment() {
    lateinit var id:String
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        Layoutinflater: LayoutInflater,
        container: ViewGroup?, savedInstantState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View = Layoutinflater.inflate(R.layout.fragment_about_booking, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BKroomID.setText(this.id)

        btn_Booking.setOnClickListener {

            if (BKroomID.text.isNullOrEmpty() or BKdateCheckIN.text.isNullOrEmpty() or BKguestID.text.isNullOrEmpty() or BKstaffID.text.isNullOrEmpty()){
                Toast.makeText(this.context,"Field is nulll or empty",Toast.LENGTH_LONG).show()
            }else{
                val data = hashMapOf<String,Any>("userID" to BKstaffID.text.toString() ,"guestID" to BKguestID.text.toString(),"roomID" to BKroomID.text.toString(),"check_in" to BKdateCheckIN.text.toString() )
                Booking(data)
            }

        }

    }

    private fun Booking(data:HashMap<String,Any>){
        val load =  SweetAlertDialog(this.context, SweetAlertDialog.PROGRESS_TYPE)
            .showCancelButton(false)
            .setTitleText("loading...");

        val success = SweetAlertDialog(this.context, SweetAlertDialog.SUCCESS_TYPE)
            .showCancelButton(false)

        val error = SweetAlertDialog(this.context, SweetAlertDialog.ERROR_TYPE)
            .showCancelButton(false)

        load.show()


        viewModel.booking(data){ status: Boolean ->
            if (status){
                load.hide()
                success.titleText = "success to Booking"
                success.show()
            }else{
                load.hide()
                error.titleText = "Fail to booking please check again"
                error.show()
            }
        }
    }

    companion object {
        fun newInstance(idR: String) = aboutBooking().apply {
            arguments = Bundle().apply {
                id = idR
            }
        }
    }
}