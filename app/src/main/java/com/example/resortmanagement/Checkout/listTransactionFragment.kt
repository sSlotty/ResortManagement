package com.example.resortmanagement.Checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.resortmanagement.Booking.listRoom
import com.example.resortmanagement.MainViewModel
import com.example.resortmanagement.R
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class listTransactionFragment : Fragment() {
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel.getBooking{
            if(it){
                val json = Gson().toJson(viewModel.transaction.value)
                Toast.makeText(this.context, json.toString(), Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this.context,"Error", Toast.LENGTH_LONG).show()
            }
        }
        return inflater.inflate(R.layout.fragment_list_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        fun newInstance(): listTransactionFragment {
            return listTransactionFragment()
        }
    }
}