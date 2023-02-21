package com.example.quantumdemo.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.quantumdemo.R
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var sign_in = findViewById<TextView>(R.id.sign_in_textview)
        sign_in.setOnClickListener{
            var intent = Intent(this, LoginActivity::class.java)
        }

        var register_textview= findViewById<TextView>(R.id.register_textview)
        register_textview.setOnClickListener {
            login()
        }
    }

    fun login(){
        var name = findViewById<EditText>(R.id.register_name_edittext).text.toString()
        var email = findViewById<EditText>(R.id.register_email_edittext).text.toString()
        var password = findViewById<EditText>(R.id.password_edittext).text.toString()
        var checkBox = findViewById<CheckBox>(R.id.checkbox)

        if (email.isNotEmpty() && password.isNotEmpty() && checkBox.isChecked) {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (password.length <= 6) {
                        Toast.makeText(
                            this,
                            "Please Enter a Strong password",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        if (it.isSuccessful) {
                            var intent =
                                Intent(this.applicationContext, LoginActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this, "Problem", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
        else{
            Toast.makeText(
                this,
                "Please fill all the details",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}