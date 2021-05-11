package com.example.resortmanagement

import android.content.Intent
import android.os.Bundle

import android.view.Gravity
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.resortmanagement.Dashboard.Dashboard
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private var accessToken: String? = null
    var listener: (()->Unit)? = null
    var intentDashboard = Intent()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<ImageButton>(R.id.btnLogin);

        val username = findViewById<EditText>(R.id.username);
        val password = findViewById<EditText>(R.id.password);
        this.intentDashboard = Intent(this, Dashboard::class.java);

        val top = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bttom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        val image4 = findViewById<ImageView>(R.id.imageView4)
        val image5 = findViewById<ImageView>(R.id.imageView5)
        val image3 = findViewById<ImageView>(R.id.imageView3)
        this.accessToken = null
        image4.startAnimation(top)
        image5.startAnimation(top)

        username.startAnimation(bttom)
        password.startAnimation(bttom)
        image3.startAnimation(bttom)
        btnLogin.startAnimation(bttom)


        btnLogin.setOnClickListener { v ->

            if (username.text.isNullOrEmpty() or password.text.isNullOrEmpty()) {
                Toast.makeText(this, "Username or password is invalid value", Toast.LENGTH_LONG)
                    .show()
            } else {
                login(username.text.toString(), password.text.toString()).let {

                }

            }
        }


    }

    fun login(username: String, password: String) {
        val values = hashMapOf<String, Any>("username" to username, "password" to password)
        viewModel.login(values)
        viewModel.login.observe(this, Observer { login ->
            val json = Gson().toJson(login)
//            accessToken.apply { login.access_token }
            val value = login.access_token.toString()
            this.accessToken = value
            Toast.makeText(this, "Response $value", Toast.LENGTH_LONG).show()
            if (!this.accessToken.isNullOrEmpty()) {
                startActivity(this.intentDashboard)
            } else {

            }
        })

    }
}