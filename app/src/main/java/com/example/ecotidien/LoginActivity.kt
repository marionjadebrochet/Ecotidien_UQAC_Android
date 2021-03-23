package com.example.ecotidien

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.size
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException

const val SIGN_IN_RC = 1

const val LOGIN_TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {

    lateinit var googleClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //val toto = intent?.getStringExtra("toto").toString()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleClient = GoogleSignIn.getClient(this, gso)

        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_WIDE)

        signInButton?.setOnClickListener{
            val intent = googleClient.signInIntent
            startActivityForResult(intent, SIGN_IN_RC)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == SIGN_IN_RC){
            try {
                val account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException::class.java)
                if (account != null) {
                    val intent =  Intent(this, MainActivity::class.java)
                }
            } catch (ex : ApiException) {
                Log.e("LOGIN_TAG", ex.statusCode.toString())
            }
        }
    }
}