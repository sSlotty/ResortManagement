package com.example.resortmanagement.Room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.resortmanagement.R

class ManageRoom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_room)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayoutManageroom,listRoomManagement.newInstance())
            .commit()
    }
    fun chanageFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayoutManageroom,fragment)
            .addToBackStack(null)
            .commit()
    }
}