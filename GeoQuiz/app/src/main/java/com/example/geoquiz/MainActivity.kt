package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var questions: ArrayList<Question>
    var position = 0
    var rightAns = 0
    var wrongAns = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadQuestions()
        setupViews()
    }

    fun loadQuestions() {
        questions = ArrayList()

        var question = Question("Es lima la capital del peru", true)
        questions.add(question)

        question = Question("Es lima la capital de chile", false)
        questions.add(question)

        question = Question("Es venezuela la capital del venezuela", false)
        questions.add(question)

        question = Question("Es madrid la capital del españa", true)
        questions.add(question)

        question = Question("Es callao la capital de bolivia", false)
        questions.add(question)

    }

    fun setupViews() {

        showSentence()

        btYes.setOnClickListener {
            validateAnswer(questions[position].answer)
        }
        btNo.setOnClickListener {
            validateAnswer(!questions[position].answer)
        }

        btNext.setOnClickListener {
            position++
            showSentence()
        }
    }

    fun showSentence() {
        if(position < questions.size) {
            tvSentence.text = questions[position].sentence
        }
        else {
            val end = "Correctas:$rightAns. Incorrectas: $wrongAns"
            tvSentence.text = end
            btYes.visibility = View.INVISIBLE
            btNo.visibility = View.INVISIBLE
            btNext.visibility = View.INVISIBLE
        }
    }

    fun validateAnswer(ans: Boolean) {
        if(ans) {
            Toast.makeText(this, "Correcto", Toast.LENGTH_LONG).show()
            rightAns++
        }
        else {
            Toast.makeText(this, "Incorrecto", Toast.LENGTH_LONG).show()
            wrongAns++
        }
    }
}