package com.karthik.karthik

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity()
{
    private var email: EditText? = null
    private var password: EditText? = null
    private var login: Button? = null
    private var signup: TextView? = null
    private lateinit var UTILS: UTILS
    private lateinit var DBHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        DBHelper = DBHelper(this)
        UTILS = UTILS(this)
        email = findViewById<EditText>(R.id.email)
        password = findViewById<EditText>(R.id.password)
        login = findViewById<Button>(R.id.signin)
        signup = findViewById<TextView>(R.id.signup)

        login!!.setOnClickListener(View.OnClickListener {
            validation()
        })
        signup!!.setOnClickListener(View.OnClickListener {
            intent = Intent(applicationContext, Signup::class.java)
            startActivity(intent)
        })
    }


    private fun validation() {
        if (!UTILS!!.isEmpty(email!!,  getString(R.string.error_message_email))) {
            return
        }
        if (!UTILS!!.isEmail(email!!,  getString(R.string.error_message_email))) {
            return
        }
        if (!UTILS!!.isEmpty(password!!,  getString(R.string.error_message_password))) {
            return
        }
        if (DBHelper!!.isUserExists(email!!.text.toString().trim { it <= ' ' }, password!!.text.toString().trim { it <= ' ' }))
        {
            intent = Intent(applicationContext, HostActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this,"Doesn't exits",Toast.LENGTH_SHORT).show()
        }
    }



}