package com.example.roomexample

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomapp.data.User
import com.example.roomapp.data.UserViewModel
import com.example.roomexample.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


//    private lateinit var userDatabase: UserDatabase
//    private lateinit var userDao: UserDao

    private lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)



        binding.floatingBtn.setOnClickListener {

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.custom_dialog)
            val window = dialog.window
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)



            val yesbtn = dialog.findViewById<Button>(R.id.yes_btn)
            val nobtn = dialog.findViewById<Button>(R.id.no_btn)
            val nameed = dialog.findViewById<EditText>(R.id.name_ed)
            val emailed = dialog.findViewById<EditText>(R.id.enail_ed)

            yesbtn.setOnClickListener {
                val name = nameed.text.toString()
                val email = emailed.text.toString()
                if (!name.isEmpty() || !email.isEmpty()){

                    val user = User(0, name,email,"1111111")
                    mUserViewModel.addUser(user)
                    dialog.dismiss()
                }else{
                    Toast.makeText(applicationContext,"Fields are empty",Toast.LENGTH_SHORT).show()
                }
            }
            nobtn.setOnClickListener {
                dialog.dismiss()
            }


            dialog.show()
        }
        val adapter  = RecyclerAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        mUserViewModel.readAllData.observe(this, Observer { user ->
            adapter.setData(user)
        })





    }
}