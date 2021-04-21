package com.example.resortmanagement.Employee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.resortmanagement.R

class Employee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameFragmentEmployee,ListEmployee.newInstance())
            .commit()
    }


    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameFragmentEmployee, fragment)
            .addToBackStack(null)
            .commit()
    }
}