package com.example.to_dopractice.views.newtask

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import androidx.core.content.ContextCompat
import com.example.to_dopractice.R
import com.example.to_dopractice.adapter.AddSubTaskAdapter
import com.example.to_dopractice.model.SubTask
import com.example.to_dopractice.model.Task
import com.example.to_dopractice.util.DateKerjaanKu
import kotlinx.android.synthetic.main.activity_new_task.*
import org.jetbrains.anko.toast

class NewTaskActivity : AppCompatActivity() {
    private lateinit var addSubTaskAdapter: AddSubTaskAdapter
//    private lateinit var dbTaskHelper: DbTaskHelper
//    private lateinit var dbSubTaskHelper: DbSubTaskHelper
    private var task: Task? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        setupActionBar()
        setupAddSubTaskAdapter()
        onClick()
    }

    private fun setupAddSubTaskAdapter() {
        addSubTaskAdapter = AddSubTaskAdapter()
        rvAddSubTask.adapter = addSubTaskAdapter
    }

    private fun onClick() {
        tbNewTask.setNavigationOnClickListener {
            finish()
        }

        btnAddDateTask.setOnClickListener {
            DateKerjaanKu.showDatePicker(this,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    val dateString = DateKerjaanKu.dateFormatSql(year, month, dayOfMonth)
                    btnAddDateTask.text = DateKerjaanKu.dateFromSqlToDateViewTask(dateString)

                    task?.mainTask?.date = dateString
                    checkIsDateFilled(true)})
        }

        btnRemoveDateTask.setOnClickListener {
            btnAddDateTask.text = null
            checkIsDateFilled(false)
        }

        btnAddSubTask.setOnClickListener{
            val subTask = SubTask(null, null, "")
            addSubTaskAdapter.addTask(subTask)
        }
    }

    private fun checkIsDateFilled(isDateFilled: Boolean) {
        if(isDateFilled){
            btnAddDateTask.background = ContextCompat.getDrawable(this, R.drawable.bg_btn_add_date_task)
            btnAddDateTask.setPadding(24, 24, 24, 24)
            btnRemoveDateTask.visibility = View.VISIBLE
        }else{
            btnAddDateTask.setBackgroundResource(0)
            btnAddDateTask.setPadding(0, 0, 0, 0)
            btnRemoveDateTask.visibility = View.GONE
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(tbNewTask)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_task_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_remove_task -> {
                toast("Remove Task")
            }
        }
        return super.onOptionsItemSelected(item)
    }

//    override fun onOptionItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.action_remove_task -> {
//                toast("Remove Task")
//            }
//        }
//        return super.onContextItemSelected(item)
//    }
}