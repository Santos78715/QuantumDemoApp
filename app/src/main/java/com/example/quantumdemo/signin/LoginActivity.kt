package com.example.quantumdemo.signin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.quantumdemo.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {
    lateinit var googleSignInClient : GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        var register_now = findViewById<TextView>(R.id.register_now)
        register_now.setOnClickListener{
            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        var login_textview = findViewById<TextView>(R.id.login_textview)
        login_textview.setOnClickListener {
            login()
        }

        var google_sign = findViewById<ImageView>(R.id.google_image)
        google_sign.setOnClickListener{

            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            googleSignInClient = GoogleSignIn.getClient(this , gso)
            signingoogle()
        }

    }

    private fun login(){

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
        requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

            var email = findViewById<EditText>(R.id.email_edittext).text.toString()
            var password = findViewById<EditText>(R.id.password_edittext).text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() ){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if(it.isSuccessful){
                        var intent = Intent(this, NewsActivity::class.java)
                        startActivity(intent)
                    }
                }
            }else {
                Toast.makeText(this, "Please Enter correct password", Toast.LENGTH_SHORT).show()
            }
    }

    fun signingoogle(){
        val signinintent = googleSignInClient.signInIntent
        launcher.launch(signinintent)

    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if (result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleresult(task)
        }
    }

    private fun handleresult(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            val account : GoogleSignInAccount? = task.result
            if(account != null){
                updateUi(account)
            }

        }else{
            Toast.makeText(this, "Task problem in main Activity", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUi(account: GoogleSignInAccount){
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                val intent = Intent(this, NewsActivity::class.java)
                intent.putExtra("email", account.email)
                intent.putExtra("password",account.displayName)
                startActivity(intent)
            }else{
                Toast.makeText(this, "hey", Toast.LENGTH_SHORT).show()
            }
        }
    }
}