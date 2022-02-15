package com.waycool.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        var et_user_name = findViewById(R.id.et_user_name) as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_submit = findViewById(R.id.btn_submit) as Button
        var btn_login = findViewById(R.id.btn_login) as Button
        var ll_login_layout = findViewById(R.id.ll_login_layout) as LinearLayout


        val sharedemail = sharedPreferences.getString("login_user", "defaultuser")
        val sharedpws = sharedPreferences.getString("password", "defaultpws")

        if (sharedemail.equals("defaultuser") && sharedpws.equals("defaultpws")) {
            Log.v("aaa", "login fail " + sharedemail.toString());
            Log.v("aaa==> ", "login fail " + sharedpws.toString());

        } else {
            val intent = Intent(this@LoginActivity, HomeActivity::class.java);
            startActivity(intent);

            this.finish()
        }

        // set on-click listener
        btn_submit.setOnClickListener {
            val user_name = et_user_name.text;
            val password = et_password.text;
            validateLogin(user_name, password, sharedPreferences)

        }
        btn_login.setOnClickListener {
            ll_login_layout.visibility = View.VISIBLE
            btn_login.visibility = View.GONE

        }
    }

    fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private fun validateLogin(
        user_name: Editable,
        password: Editable,
        sharedPreferences: SharedPreferences
    ) {

        if (user_name.toString() == "") {
            this.toast("Please Enter Email")
        }
        if (password.toString() == "") {
            this.toast("Please Enter Password")
        }
        Log.v("aaa", "user_name " + user_name.toString());
        Log.v("aaa==> ", "password " + password.toString());

        if (user_name.toString().equals("testapp@google.com") && password.toString()
                .equals("Test@123456")
        ) {

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("login_user", user_name.toString())
            editor.putString("password", password.toString())
            editor.apply()
            editor.commit()

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            this.finish()

        } else {
            this.toast("Please Enter correct username & password.")
        }

    }
}