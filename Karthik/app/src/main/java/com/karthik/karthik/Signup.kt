package com.karthik.karthik

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Signup : AppCompatActivity() {

    private var userName: EditText? = null
    private var email: EditText? = null
    private var password: EditText? = null
    private var login: Button? = null
    private var signin: TextView? = null
    private lateinit var UTILS: UTILS
    private lateinit var DBHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        DBHelper = DBHelper(this)
        UTILS = UTILS(this)
        userName = findViewById<EditText>(R.id.userName)
        email = findViewById<EditText>(R.id.email)
        password = findViewById<EditText>(R.id.password)
        login = findViewById<Button>(R.id.signin)
        signin = findViewById<TextView>(R.id.login)

        login!!.setOnClickListener(View.OnClickListener {
            validation()
        })

        signin!!.setOnClickListener(View.OnClickListener {
            intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
        })
    }

    private fun validation() {
        if (!UTILS!!.isEmpty(email!!, getString(R.string.error_message_email))) {
            return
        }
        if (!UTILS!!.isEmail(email!!,  getString(R.string.error_message_email))) {
            return
        }
        if (!UTILS!!.isEmpty(password!!,  getString(R.string.error_message_email))) {
            return
        }
        if (!UTILS!!.isEmpty(userName!!,  getString(R.string.error_message_email))) {
            return
        }
        if (!DBHelper!!.isUserExists(email!!.text.toString().trim())) {
            val user = User(name = userName!!.text.toString().trim(),email = email!!.text.toString().trim(), password = password!!.text.toString().trim())
            DBHelper!!.addUser(user)
            Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show()
            intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
        } else
        {
            Toast.makeText(this,"Exists",Toast.LENGTH_SHORT).show()
        }
    }

}