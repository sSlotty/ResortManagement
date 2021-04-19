package com.example.resortmanagement.Booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.resortmanagement.R

/**
 * A simple [Fragment] subclass.
 * Use the [aboutBooking.newInstance] factory method to
 * create an instance of this fragment.
 */
class aboutBooking : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        Layoutinflater: LayoutInflater,
        container: ViewGroup?, savedInstantState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return Layoutinflater.inflate(R.layout.fragment_about_booking, container, false)
    }

    companion object {
        fun newInstance(): aboutBooking {
            return aboutBooking()
        }
    }
}