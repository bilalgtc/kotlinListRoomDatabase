package com.example.roomexample

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.data.User
import com.example.roomapp.data.UserViewModel

class RecyclerAdapter() :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var userList = emptyList<User>()
    private lateinit var mUserViewModel: UserViewModel
    lateinit var context: Context

    constructor(context: Context): this(context) {

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newlist = userList[position]
        holder.name.text = newlist.firstName
        holder.email.text = newlist.email

        holder.updatebtn.setOnClickListener {
           val intent = Intent(it.context,MainActivity2::class.java)
            intent.putExtra("id",newlist.id.toString())
            intent.putExtra("name",newlist.firstName)
            intent.putExtra("email",newlist.email)
            it.context.startActivity(intent)
            }

        holder.deletebtn.setOnClickListener {
            mUserViewModel = ViewModelProvider(it.contextcontext).get(UserViewModel::class.java)
            mUserViewModel.deleteUser(newlist)

        }

    }


    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.name_txt)
        val email = itemView.findViewById<TextView>(R.id.emil_txt)
        val updatebtn = itemView.findViewById<Button>(R.id.btn_update)
        val deletebtn = itemView.findViewById<Button>(R.id.btn_close)



    }

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }
}