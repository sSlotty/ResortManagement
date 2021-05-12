package com.example.resortmanagement.Guest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.resortmanagement.Employee.ListEmployee
import com.example.resortmanagement.MainViewModel
import com.example.resortmanagement.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class GuestActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest)

        val listGuestFragment = listGuestFragment.newInstance()
        listGuestFragment.callback = {
            changeFragment(editGuestFragment.newInstance(it))
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameFragmentGuest, listGuestFragment)
            .commit()


//        viewModel.login.value

    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameFragmentGuest, fragment)
            .addToBackStack(null)
            .commit()
    }
}