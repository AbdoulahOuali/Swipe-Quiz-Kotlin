package com.abdoulahouali.level2.task2.swipequizkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    // Initializing an empty ArrayList to be filled with quiz statements
    private val quizStatements = arrayListOf<QuizStatement>()
    private val quizStatementAdapter = QuizStatementAdapter(quizStatements)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        quizStatements.add(QuizStatement(getString(R.string.statement_1), false))
        quizStatements.add(QuizStatement(getString(R.string.statement_2), false))
        quizStatements.add(QuizStatement(getString(R.string.statement_3), true))
        quizStatements.add(QuizStatement(getString(R.string.statement_4), true))

        initViews()
    }

    private fun initViews() {
        quiz_recycler_view.layoutManager =
            LinearLayoutManager(this@QuizActivity, RecyclerView.VERTICAL, false)
        quiz_recycler_view.adapter = quizStatementAdapter

        quiz_recycler_view.addItemDecoration(
            DividerItemDecoration(
                this@QuizActivity,
                DividerItemDecoration.VERTICAL
            )
        )
        createItemTouchHelper().attachToRecyclerView(quiz_recycler_view)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {


        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.layoutPosition

                var check = checkAnswer(quizStatements[position], direction)
                if (check == "correct") {
                    quizStatements.removeAt(position)
                }
                quizStatementAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun checkAnswer(quizStatement: QuizStatement, givenAnswer: Int): String {
        var answer = ""
        val leftSwipe = 4
        val rightSwipe = 8
        when (quizStatement) {
            quizStatements[0], quizStatements[1] ->
                when (givenAnswer) {
                    rightSwipe -> answer = onAnswerCorrect()
                    leftSwipe -> answer = onAnswerIncorrect()
                }
            quizStatements[2], quizStatements[3] ->
                when (givenAnswer) {
                    rightSwipe -> answer = onAnswerIncorrect()
                    leftSwipe -> answer = onAnswerCorrect()
                }
        }
        return answer
    }

    private fun onAnswerCorrect(): String {
        Snackbar.make(quiz_recycler_view, getText(R.string.correct), Snackbar.LENGTH_SHORT).show()
        return "correct"
    }

    private fun onAnswerIncorrect(): String {
        Snackbar.make(quiz_recycler_view, getText(R.string.incorrect), Snackbar.LENGTH_SHORT).show()

        return "incorrect"
    }

}

