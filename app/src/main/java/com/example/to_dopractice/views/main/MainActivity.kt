package com.example.to_dopractice.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.to_dopractice.R
import com.example.to_dopractice.views.home.HomeFragment
import com.example.to_dopractice.views.newtask.NewTaskActivity
import com.example.to_dopractice.views.taskcomplete.TaskCompleteFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNavigation()
        btnAddTask.setOnClickListener {
            startActivity<NewTaskActivity>()
        }
    }

    private fun setupBottomNavigation() {
        btnMain.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_home -> {
                    openFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_task_complate -> {
                    openFragment(TaskCompleteFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
        btnMain.selectedItemId = R.id.action_home
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fraeMain, fragment)
            .addToBackStack(null)
            .commit()
    }
}