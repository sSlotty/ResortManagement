package com.example.resortmanagement.Guest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.resortmanagement.Employee.ListEmployee
import com.example.resortmanagement.R

class GuestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameFragmentGuest, listGuestFragment.newInstance())
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