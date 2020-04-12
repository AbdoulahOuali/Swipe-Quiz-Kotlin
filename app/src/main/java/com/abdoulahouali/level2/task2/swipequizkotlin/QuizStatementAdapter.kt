package com.abdoulahouali.level2.task2.swipequizkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.quiz_statement.view.*

class QuizStatementAdapter(private val quizStatements: List<QuizStatement>) :
    RecyclerView.Adapter<QuizStatementAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(quizStatement: QuizStatement) {
            itemView.quiz_statement.text = quizStatement.quizStatementText

            itemView.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.quiz_statement, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return quizStatements.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(quizStatements[position])
    }

}