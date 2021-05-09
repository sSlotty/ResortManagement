package com.example.resortmanagement

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.resortmanagement.Dashboard.Dashboard
import com.example.resortmanagement.repository.Repository


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<ImageButton>(R.id.btnLogin);

        val username = findViewById<EditText>(R.id.username);
        val password = findViewById<EditText>(R.id.password);
        val intentDashboard = Intent(this, Dashboard::class.java);

        val top = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        val bttom = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)
        val image4 = findViewById<ImageView>(R.id.imageView4)
        val image5 = findViewById<ImageView>(R.id.imageView5)
        val image3 = findViewById<ImageView>(R.id.imageView3)

        image4.startAnimation(top)
        image5.startAnimation(top)

        username.startAnimation(bttom)
        password.startAnimation(bttom)
        image3.startAnimation(bttom)
        btnLogin.startAnimation(bttom)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getStaff()
        viewModel.myReposponse.observe(this, Observer { response ->
            Log.d("Response",response._id)
            Log.d("Response",response.name)
            Log.d("Response",response.salary.toString())
            Log.d("Response",response.job_position)

        })

        btnLogin.setOnClickListener { v ->

            startActivity(intentDashboard)

            if(username.text.isNullOrEmpty() or password.text.isNullOrEmpty()){
                Toast.makeText(this, "Username or password is invalid value", Toast.LENGTH_LONG).show()
            }else{
                login(username.toString(), password.toString())
            }
        }
    }

    fun login(username: String, password: String){
        val values = mapOf("username" to username,"password" to password)

        val toast = Toast.makeText(
            this,
            "Username is : $username, Password is : $password", Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.CENTER or Gravity.CENTER_HORIZONTAL, 0, 0)
        toast.show()
    }
}