package com.example.resortmanagement

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.resortmanagement.Dashboard.Dashboard


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<ImageButton>(R.id.btnLogin);

        val username = findViewById<EditText>(R.id.username).text;
        val password = findViewById<EditText>(R.id.password).text;
        val intentDashboard = Intent(this, Dashboard::class.java);


        btnLogin.setOnClickListener { v ->
            
            startActivity(intentDashboard)
            if(username.isNullOrEmpty() or password.isNullOrEmpty()){
                Toast.makeText(this, "Username or password is invalid value", Toast.LENGTH_LONG).show()
            }else{
                login(username.toString(), password.toString())
            }
        }
    }

    fun login(username: String, password: String){
        val toast = Toast.makeText(
            this,
            "Username is : $username, Password is : $password", Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.CENTER or Gravity.CENTER_HORIZONTAL, 0, 0)
        toast.show()
    }
}