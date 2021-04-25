package com.karthik.karthik

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class Home : Fragment() {
    private var recyclerView: RecyclerView? = null
    private lateinit var list: ArrayList<User>
    private lateinit var adapter: recycler
    private lateinit var dbHelper: DBHelper
    private lateinit var cc:Context

    override fun onAttach(context: Context)
    {
        super.onAttach(context)
        this.cc=context
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view!!.findViewById(R.id.recyclerView) as RecyclerView
        list = ArrayList()
        dbHelper = DBHelper(cc)
        val fetchTask = FetchTask()
        fetchTask.execute()
        adapter = recycler(list)
        recyclerView!!.adapter = adapter
        return view
    }

    inner class FetchTask : AsyncTask<Void, Void, ArrayList<User>>() {
        override fun doInBackground(vararg p0: Void?): ArrayList<User> {
            return dbHelper.getAllUser()
        }
        override fun onPostExecute(result: ArrayList<User>?) {
            super.onPostExecute(result)
            list.clear()
            list.addAll(result!!)
            adapter.notifyDataSetChanged()
        }
    }
}


