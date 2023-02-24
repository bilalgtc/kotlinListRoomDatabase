package com.example.roomexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.data.User
import com.example.roomapp.data.UserViewModel
import com.example.roomexample.databinding.ActivityMain2Binding
import com.example.roomexample.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")

        binding.nameEdt.setText(name)
        binding.emailEdt.setText(email)


        binding.btnUpdate.setOnClickListener {
            val newname = binding.nameEdt.text.toString()
            val newmail = binding.emailEdt.text.toString()

            userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

            val updateduser = User(Integer.parseInt(id), newname, newmail, "11111")
            userViewModel.updateUser(updateduser)

            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        }

    }
}