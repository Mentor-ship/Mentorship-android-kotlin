package com.madisadyk.mentorship_android_kotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.madisadyk.mentorship_android_kotlin.R
import com.madisadyk.mentorship_android_kotlin.data.model.Vacancy
import kotlinx.android.synthetic.main.popular_job_list_item.view.*

class PopularVacancyAdapter : RecyclerView.Adapter<PopularVacancyAdapter.VacancyViewHolder>() {
    inner class VacancyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Vacancy>() {
        override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyViewHolder {
        return VacancyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.popular_job_list_item,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: VacancyViewHolder, position: Int) {
        val vacancy = differ.currentList[position]

        holder.itemView.apply {
            card_image.setImageResource(R.drawable.dummy_img)
            job_name.text = vacancy.name_of_vacancy
            duty_type.text = vacancy.duty
            job_salary.text = "$${vacancy.salary}/h"
        }
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    private var onItemClickListener: ((Vacancy) -> Unit)? = null

    fun setOnItemClickListener(listener: (Vacancy) -> Unit) {
        onItemClickListener = listener
    }
}