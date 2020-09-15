package com.example.to_dopractice.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dopractice.R
import com.example.to_dopractice.model.SubTask
import kotlinx.android.synthetic.main.item_sub_tasks.view.*
import kotlinx.android.synthetic.main.item_task.view.*

class SubTaskAdapter : RecyclerView.Adapter<SubTaskAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(subTask: SubTask){
            itemView.tvTitleSubTask.text = subTask.title
            if (subTask.isComplete){
                completeSubTask()
            }else{
                inCompleteSubTask()
            }

            itemView.btnSubTask.setOnClickListener{
                if(subTask.isComplete!!){
                    inCompleteSubTask()
                    subTask.isComplete = false
                } else {
                    completeSubTask()
                    subTask.isComplete = true
                }
            }
        }

        private fun completeSubTask() {
            itemView.btnSubTask.setImageResource(R.drawable.ic_complate_task_done_24)
            itemView.tvTitleSubTask.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }

        private fun inCompleteSubTask() {
            itemView.btnSubTask.setImageResource(R.drawable.ic_baseline_panorama_fish_eye_24)
            itemView.tvTitleSubTask.paintFlags = Paint.ANTI_ALIAS_FLAG
        }
    }

    private  lateinit var subTasks: List<SubTask>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(
        R.layout.item_sub_tasks, parent, false))

    override fun getItemCount(): Int = subTasks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(subTasks[position])
    }

    fun setData(subTasks: List<SubTask>){
        this.subTasks = subTasks
        notifyDataSetChanged()
    }
}