package com.example.resortmanagement.Checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.resortmanagement.Booking.aboutBooking
import com.example.resortmanagement.R

class Bill : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill)
        val transac = listTransactionFragment.newInstance()

//        listRoom.callback = {
//            changeFragment(aboutBooking.newInstance(it))
//        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameBill, transac)
            .commit()

    }


fun changeFragment(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.frameBill, fragment)
        .addToBackStack(null)
        .commit()
}
}