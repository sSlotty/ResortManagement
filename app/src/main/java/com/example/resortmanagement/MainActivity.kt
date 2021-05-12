package com.example.resortmanagement

import android.content.Intent
import android.os.Bundle
import android.os.Handler
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
import com.example.resortmanagement.repository.service.SharedPrefsUtil
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
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

                    login(username.text.toString(), password.text.toString()).let { res ->
                    Log.i("Res", res.toString())
                }

            }
        }

    }



    fun login(username: String, password: String){
        val values = hashMapOf<String, Any>("username" to username, "password" to password)
        val res = null

        val load =  SweetAlertDialog(this@MainActivity, SweetAlertDialog.PROGRESS_TYPE)
            .showCancelButton(false)
            .setTitleText("Loading...");
        load.show()

        val onSuccess = SweetAlertDialog(this@MainActivity,SweetAlertDialog.SUCCESS_TYPE)
            .showCancelButton(false)


        val onError = SweetAlertDialog(this@MainActivity,SweetAlertDialog.ERROR_TYPE)
            .setTitleText("Username or password is incorrect.")
            .showCancelButton(false)



        viewModel.login(values){ status,text ->
            if(status){
//                SharedPrefsUtil.setString(this,"USER_ID",username)

                load.hide()
//                onSuccess.show();
//                onSuccess.titleText = "Login success"
//                Toast.makeText(this, "Response $status $text", Toast.LENGTH_LONG).show()
                startActivity(this.intentDashboard)
            } else {
//                Toast.makeText(this, "Response $status", Toast.LENGTH_LONG).show()
                load.hide()
                onError.show()
                ////
            }
        }

//        viewModel.login.observe(this, Observer { login ->
//            Handler().postDelayed({ //
//                val json = Gson().toJson(login)
//                val value = login.access_token.toString()
//                this.accessToken = value
//                Toast.makeText(this, "Response $value", Toast.LENGTH_LONG).show()
//                if (!this.accessToken.isNullOrEmpty()) {
//                    startActivity(this.intentDashboard)
//                } else {
//
//                }
//            }, 2000L) //
//        })

    }
}