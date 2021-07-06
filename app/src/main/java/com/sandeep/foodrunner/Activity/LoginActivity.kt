package com.sandeep.foodrunner.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.sandeep.foodrunner.R

class LoginActivity : AppCompatActivity() {

    /*Declaring the different variables used for this activity*/
    private lateinit var registerYourself: TextView
    private lateinit var login: Button
    private lateinit var etMobileNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var txtForgotPassword: TextView

    /*Life-cycle method of the activity*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Linking the view*/
        setContentView(R.layout.activity_login)


        /*Initialising the views with the ones defined in the XML*/
        etMobileNumber = findViewById(R.id.etMobileNumber)
        etPassword = findViewById(R.id.etPassword)
        txtForgotPassword = findViewById(R.id.txtForgotPassword)
        registerYourself = findViewById(R.id.txtRegisterYourself)
        login = findViewById(R.id.btnLogin)

        /*Handling the clicks using the setOnClickListener method*/
//        txtForgotPassword.setOnClickListener {
//            startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
//        }
//        registerYourself.setOnClickListener {
//            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
//        }


        login.setOnClickListener {

            /*Declaring the intent which sets up the route for the navigation of the activity*/
            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)


            /*Starting the new activity by sending the intent in the startActivity method*/
            startActivity(intent)
        }

    }
}