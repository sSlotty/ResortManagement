package com.example.resortmanagement

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.resortmanagement.Dashboard.Dashboard
import com.example.resortmanagement.model.LoginRes
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private var accessToken: String? = null
    var listener: (() -> Unit)? = null
    var intentDashboard = Intent()
    private val uiScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var visible = false
        val btnLogin = findViewById<ImageButton>(R.id.btnLogin);

        val username = findViewById<EditText>(R.id.username);
        val password = findViewById<EditText>(R.id.password);
        this.intentDashboard = Intent(this, Dashboard::class.java);

        val top = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bttom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        val image4 = findViewById<ImageView>(R.id.imageView4)

        val image3 = findViewById<ImageView>(R.id.imageView3)
        val panelLogin = findViewById<LinearLayout>(R.id.panelLogin)
        this.accessToken = null

        image4.startAnimation(top)
        panelLogin.startAnimation(bttom)



        btnLogin.setOnClickListener { v ->

            if (username.text.isNullOrEmpty() or password.text.isNullOrEmpty()) {
                Toast.makeText(this, "Username or password is invalid value", Toast.LENGTH_LONG)
                    .show()
            } else {
//                uiScope.launch {
//
//                    }
                login(username.text.toString(), password.text.toString()).let { res ->
                    Log.i("Res", res.toString())
                }

            }
        }

    }



    fun login(username: String, password: String){
        val values = hashMapOf<String, Any>("username" to username, "password" to password)
        val res = null
        viewModel.login(values)
        viewModel.login.observe(this, Observer { login ->
            val json = Gson().toJson(login)


            val value = login.access_token.toString()
            this.accessToken = value
            Toast.makeText(this, "Response $value", Toast.LENGTH_LONG).show()
            if (!this.accessToken.isNullOrEmpty()) {
                SweetAlertDialog(this@MainActivity, SweetAlertDialog.PROGRESS_TYPE)
                    .showCancelButton(false)
                    .show()
                startActivity(this.intentDashboard)
            } else {

            }


        })

    }
}