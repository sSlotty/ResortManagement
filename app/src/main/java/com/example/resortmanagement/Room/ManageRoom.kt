package com.example.resortmanagement.Room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.resortmanagement.Guest.listGuestFragment
import com.example.resortmanagement.R

class ManageRoom : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listRoomManagement = listRoomManagement.newInstance()

        setContentView(R.layout.activity_manage_room)

        listRoomManagement.callback = {
            chanageFragment(editRoomFragment.newInstance(it))
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayoutManageroom,listRoomManagement)
            .commit()
    }
    fun chanageFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayoutManageroom,fragment)
            .addToBackStack(null)
            .commit()
    }
}