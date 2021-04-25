package com.karthik.karthik


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView


class recycler(private val listUsers: List<User>) : RecyclerView.Adapter<recycler.viewhUser>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewhUser {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return viewhUser(itemView)
    }

    override fun onBindViewHolder(holder: viewhUser, position: Int) {
        holder.username.text = listUsers[position].name
        holder.emailtext.text = listUsers[position].email
        holder.password.text = listUsers[position].password

    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    inner class viewhUser(view: View) : RecyclerView.ViewHolder(view) {
        var username: AppCompatTextView
        var emailtext: AppCompatTextView
        var password: AppCompatTextView
        init {
            username = view.findViewById(R.id.textViewName) as AppCompatTextView
            emailtext = view.findViewById(R.id.textViewEmail) as AppCompatTextView
            password = view.findViewById(R.id.password) as AppCompatTextView
        }
    }
}