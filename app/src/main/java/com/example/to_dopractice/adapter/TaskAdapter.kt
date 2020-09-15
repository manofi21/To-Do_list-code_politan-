package com.example.to_dopractice.adapter

import android.graphics.Paint
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dopractice.R
import com.example.to_dopractice.model.Task
import kotlinx.android.synthetic.main.item_task.view.*

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(
            task: Task
        ) {
            itemView.tvTitleTask.text = task.mainTask?.title

            if (task.mainTask?.isComplete!!){
                completeTask()
            }else{
                inCompleteTask()
            }

            if (task.mainTask!!.date != null && task.mainTask!!.date!!.isNotEmpty()){
                showDateTask()
                itemView.tvDateTask.text = task.mainTask!!.date
            }else{
                hideDateTask()
            }

            if (task.subTask != null){
                showSubTasks()
                val subTaskAdapter = SubTaskAdapter()
                subTaskAdapter.setData(task.subTask)

                itemView.rvSubTask.adapter = subTaskAdapter
            }else{
                hideSubTasks()
            }
            itemView.btnDoneTask.setOnClickListener{
                if(task.mainTask.isComplete){
                    inCompleteTask()
                    task.mainTask.isComplete = false
                } else {
                    completeTask()
                    task.mainTask.isComplete = true
                }
            }
        }

        private fun completeTask() {
            itemView.btnDoneTask.setImageResource(R.drawable.ic_complate_task_done_24)
            itemView.tvTitleTask.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        private fun inCompleteTask() {
            itemView.btnDoneTask.setImageResource(R.drawable.ic_baseline_panorama_fish_eye_24)
            itemView.tvTitleTask.paintFlags = Paint.ANTI_ALIAS_FLAG
        }

        private fun hideSubTasks() {
            itemView.lineTask.visibility = View.GONE
            itemView.rvSubTask.visibility = View.GONE
        }

        private fun showSubTasks() {
            itemView.lineTask.visibility = View.VISIBLE
            itemView.rvSubTask.visibility = View.VISIBLE
        }

        private fun hideDateTask() {
            itemView.containerDateTask.visibility = View.GONE
        }

        private fun showDateTask() {
            itemView.containerDateTask.visibility = View.VISIBLE
        }

    }

    private var tasks = mutableListOf<Task>()
    private var listener : ((Task) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false))

    override fun getItemCount(): Int = tasks.size

    fun setData(tasks: List<Task>){
        this.tasks = tasks as MutableList<Task>
        notifyDataSetChanged()
    }

    fun onClick(listener: (Task) -> Unit){
        this.listener = listener
    }

    private fun deleteDataTask(position: Int){
        tasks.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, tasks.size)
    }

    fun deleteAllDataTask(){
        tasks.removeAll(tasks)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])
    }
}