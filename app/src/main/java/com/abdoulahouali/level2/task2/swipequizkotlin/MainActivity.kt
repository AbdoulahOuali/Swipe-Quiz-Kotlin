package com.abdoulahouali.level2.task2.swipequizkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Initializing an empty ArrayList to be filled with quiz statements
    private val quizStatements = arrayListOf<QuizStatement>()
    private val quizStatementAdapter = QuizStatementAdapter(quizStatements)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quizStatements.add(QuizStatement(getString(R.string.statement_1), false))
        quizStatements.add(QuizStatement(getString(R.string.statement_2), false))
        quizStatements.add(QuizStatement(getString(R.string.statement_3), true))
        quizStatements.add(QuizStatement(getString(R.string.statement_4), true))

        initViews()
    }

    fun initViews() {
        quiz_recycler_view.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        quiz_recycler_view.adapter = quizStatementAdapter

        quiz_recycler_view.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

    }
}
