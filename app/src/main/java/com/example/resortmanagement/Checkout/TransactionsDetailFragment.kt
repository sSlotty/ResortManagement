package com.example.resortmanagement.Checkout

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import com.example.resortmanagement.MainViewModel
import com.example.resortmanagement.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_edit_guest.*
import kotlinx.android.synthetic.main.fragment_transactions_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class TransactionsDetailFragment : Fragment() {
    lateinit var id:String
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment'
        val v:View =  inflater.inflate(R.layout.fragment_transactions_detail, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transaction = hashMapOf<String,Any>("transactionID" to this.id)
        viewModel.getBookingByID(transaction){ status ->
//            Toast.makeText(context,status.toString(),Toast.LENGTH_LONG).show()

            if (status){
                val data = viewModel.TransasctionByID.value
                val value = data?.get(0)
                transacDT_transacID.setText(value?.transactionID.toString())
                transacDT_CheckIN.setText(value?.check_in.toString())
                transacDT_CheckOUT.setText(value?.check_out.toString())
                transacDT_TotalPrice.setText(value?.total_bill.toString())
                transacDT_guestID.setText(value?.guestID.toString())
                transacDT_guestTel.setText(value?.guestTel.toString())
                transacDT_guestName.setText(value?.guestName.toString())
                transacDT_roomID.setText(value?.roomNum.toString())
                transacDT_roomPrice.setText(value?.roomPrice.toString())
            }else{

            }
        }
//        Toast.makeText(context,this.id,Toast.LENGTH_LONG).show()
        btnCheckOut.setOnClickListener {
           if(inputDateCheckOut.text.isNullOrEmpty()){
               Toast.makeText(context,"Date checkout is null",Toast.LENGTH_LONG).show()
           }else{
               val checkout = hashMapOf<String,Any>("transactionID" to this.id,"check_out" to inputDateCheckOut.text.toString())
               viewModel.checkOut(checkout){
                   if(it){
                       Toast.makeText(this.context,"Checkout Success",Toast.LENGTH_LONG).show()
                       val ft: FragmentTransaction = this.requireFragmentManager().beginTransaction()
                       ft.detach(this)
                       ft.attach(this)
                       ft.commit()
                   }else{
                       Toast.makeText(this.context,"This transaction is already checkout",Toast.LENGTH_LONG).show()

                   }
               }
           }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(idT:String) = TransactionsDetailFragment().apply {
            arguments = Bundle().apply {
                id = idT
            }
        }
    }
}