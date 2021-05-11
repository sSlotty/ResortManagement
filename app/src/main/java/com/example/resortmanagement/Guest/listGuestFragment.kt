package com.example.resortmanagement.Guest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import com.example.resortmanagement.Booking.Booking
import com.example.resortmanagement.Booking.aboutBooking
import com.example.resortmanagement.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [listGuestFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class listGuestFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v:View = inflater.inflate(R.layout.fragment_list_guest, container, false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAddGuest = view.findViewById<ImageButton>(R.id.btnAddGuest)

        btnAddGuest.setOnClickListener {
            (context as GuestActivity).changeFragment(addGuestFragment.newInstance())
//            Toast.makeText(this.context,"Test Add Guest",Toast.LENGTH_LONG).show()
        }
    }

    companion object {

        fun newInstance(): listGuestFragment {
            return listGuestFragment()
        }
    }
}