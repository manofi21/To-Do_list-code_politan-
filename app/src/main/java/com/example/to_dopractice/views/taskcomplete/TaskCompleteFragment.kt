package com.example.to_dopractice.views.taskcomplete

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.to_dopractice.R
import com.example.to_dopractice.adapter.TaskAdapter
import com.example.to_dopractice.model.SubTask
import com.example.to_dopractice.model.Task
import com.example.to_dopractice.repository.TaskRepository
import kotlinx.android.synthetic.main.fragment_task_complete.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TaskCompleteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskCompleteFragment : Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_complete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tasks = TaskRepository.getDataTasks(context)
        if(tasks != null){
            for (task: Task in tasks.tasks!!){
                task.mainTask?.isComplete = true
                if (task.subTask != null) {
                    for (subTask: SubTask in task.subTask){
                        subTask.isComplete = true
//                        subTask.title = "tolol"
                    }
                }
            }

            showTaskComplete()
            val taskAdapter = TaskAdapter()
            taskAdapter.setData(tasks.tasks)
            rvTaskComplete.adapter = taskAdapter
        } else {
            hideTaskComplete()
        }
    }

    private fun showTaskComplete() {
        rvTaskComplete.visibility = View.VISIBLE
        layoutEmptyTaskComplete.visibility = View.GONE
        fabDeleteTaskComplete.visibility = View.VISIBLE
    }

    private fun hideTaskComplete() {
        rvTaskComplete.visibility = View.GONE
        layoutEmptyTaskComplete.visibility = View.VISIBLE
        fabDeleteTaskComplete.visibility = View.GONE
    }
}