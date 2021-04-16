package com.example.resortmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val booking = Intent(this,Booking::class.java)
        val manageroom = Intent(this,ManageRoom::class.java)
        val bill = Intent(this,Bill::class.java)
        val employee = Intent(this,Employee::class.java)

        var imageBooking = findViewById<ImageView>(R.id.btnBooking)
        imageBooking.setOnClickListener{view->
            startActivity(booking)
        }

        var imageManage = findViewById<ImageView>(R.id.btnManage)
        imageManage.setOnClickListener{view->
            startActivity(manageroom)
        }

        var imageBill = findViewById<ImageView>(R.id.btnBill)
        imageBill.setOnClickListener{view->
            startActivity(bill)
        }

        var imageEmployee = findViewById<ImageView>(R.id.btnEmployee)
        imageEmployee.setOnClickListener{view->
            startActivity(employee)
        }
    }
}