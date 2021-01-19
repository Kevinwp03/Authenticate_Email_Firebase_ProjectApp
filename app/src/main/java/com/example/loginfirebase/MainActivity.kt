package com.example.loginfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance();


        btnRegister.setOnClickListener {
            // Log.e("Action", "Clicke") -> hanya untuk mengecek  apaka button sudah berfungsi

            if (editEmail.text.trim().toString().isNotEmpty() || editPassword.text.trim().isNotEmpty()) {
                createUser(editEmail.text.trim().toString(), editPassword.text.trim().toString())
                Toast.makeText(this, "Input Provided", Toast.LENGTH_LONG).show()

            } else {

                Toast.makeText(this, "Input Provided", Toast.LENGTH_LONG).show()

            }
        }
    }

        fun createUser(email: String, password: String){
            auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){ task ->

                        if (task.isSuccessful){
                            Log.e("Text Massage", "Succesfull..")

                            var intent = Intent(this, DashboardActivity::class.java)
                            startActivity(intent)
                        }else{
                            Log.e("Text Massage", "Succesfull.." + task.exception)
                        }

                    }
        }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser

        if (user != null){
            var intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}