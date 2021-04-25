package com.karthik.karthik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class HostActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        addFragment(Home(),R.id.frameLayout)
    }
    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }
    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction{replace(frameId, fragment)}
    }
    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }
}