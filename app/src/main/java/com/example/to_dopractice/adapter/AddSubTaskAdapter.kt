package com.example.to_dopractice.adapter

import android.graphics.Paint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dopractice.R
import com.example.to_dopractice.model.SubTask
import kotlinx.android.synthetic.main.item_add_sub_task.view.*

class AddSubTaskAdapter: RecyclerView.Adapter<AddSubTaskAdapter.ViewHolder>(){
  inner  class ViewHolder (view: View) :RecyclerView.ViewHolder(view) {
        fun bind(subTask: SubTask) {
            if(subTask.title != null){
                itemView.etTitleSubTask.setText(subTask.title)
           }
            if(subTask.isComplete){
                completeTask()
            }else {
                inCompleteTask()
            }

            itemView.btnRemoveSubTask.setOnClickListener {
                deleteTask(adapterPosition)
            }

            itemView.etTitleSubTask.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    subTask.title = p0.toString()
                    updateTask(subTask, adapterPosition)
                }

            })
        }

        private fun inCompleteTask() {
            itemView.btnCompleteSubTask.setImageResource(R.drawable.ic_baseline_panorama_fish_eye_24)
            itemView.etTitleSubTask.paintFlags = Paint.ANTI_ALIAS_FLAG
        }

        private fun completeTask() {
            itemView.btnCompleteSubTask.setImageResource(R.drawable.ic_baseline_panorama_fish_eye_24)
            itemView.etTitleSubTask.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private var listAddSubTask = mutableListOf<SubTask>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_add_sub_task, parent, false))

    override fun getItemCount(): Int = listAddSubTask.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listAddSubTask[position])
    }

    fun addTask(subTask: SubTask){
        listAddSubTask.add(subTask)
        notifyItemInserted(listAddSubTask.size - 1)
    }

    fun deleteTask(position: Int){
        listAddSubTask.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listAddSubTask.size)
    }

    fun updateTask(subTask: SubTask, position: Int){
        listAddSubTask[position] = subTask
    }

}