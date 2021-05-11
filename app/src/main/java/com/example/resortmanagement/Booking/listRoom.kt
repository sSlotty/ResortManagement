package com.example.resortmanagement.Booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.example.resortmanagement.MainViewModel
import com.example.resortmanagement.R
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [listRoom.newInstance] factory method to
 * create an instance of this fragment.
 */
class listRoom : Fragment() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(Layoutinflater: LayoutInflater,
                              container: ViewGroup?, savedInstantState: Bundle?): View?  {



        return Layoutinflater.inflate(R.layout.fragment_list_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val btnChangeFragment = view.findViewById<ImageButton>(R.id.btnChangeFragment)
//        btnChangeFragment.setOnClickListener {
//            (context as Booking).changeFragment(aboutBooking.newInstance())
//        }

        viewModel.getRoomStatus()
        viewModel.roomStatus.observe(this.viewLifecycleOwner, Observer { roomStatus ->
            val json = Gson().toJson(roomStatus)
            Toast.makeText(this.context, "Response $json", Toast.LENGTH_LONG).show()
        })



    }

    companion object {
        fun newInstance(): listRoom {
            return listRoom()
        }
    }
}