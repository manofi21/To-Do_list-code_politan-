package com.example.to_dopractice.repository

import android.content.Context
import com.example.to_dopractice.model.Task
import com.example.to_dopractice.model.TasksExample
import com.google.gson.Gson
import java.io.IOException


// statik class di java
object TaskRepository {
    fun getDataTasks(context: Context?): TasksExample?{
        val json: String?
        try {
            val inputStream = context?.assets?.open("tasks.json")
            json = inputStream?.bufferedReader().use { it?.readText() }
        }catch (e: IOException){
            e.printStackTrace()
            return null
        }

        return Gson().fromJson(json, TasksExample::class.java)
    }

}