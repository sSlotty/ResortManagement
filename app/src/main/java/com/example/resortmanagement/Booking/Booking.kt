package com.example.resortmanagement.Booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.resortmanagement.R

class Booking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        // Init Fragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameFragmentGuest, listRoom.newInstance())
            .commit()

    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameFragmentGuest, fragment)
            .addToBackStack(null)
            .commit()
    }
}
