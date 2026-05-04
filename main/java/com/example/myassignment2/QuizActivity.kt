package com.example.myassignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    var index = 0
    var score = 0
    lateinit var questionText: TextView
    lateinit var feedbackText: TextView
    val questions = arrayOf(
        "We only use 10% of our brains, the rest is dormant",
        "Human hearts beat 100000 times every day",
        "Shaving your hair makes it grow back thicker and darker",
        "Honey never spoils",
        "Lightning never strikes the same place twice"
    )

    val answers = arrayOf(false, true, false, true, false)
    val explanations = arrayOf(
        "Myth: All brain regions are active some point and most are active all the time.",
        "Hack: Human heart can beat up to 100800 times in 24 hours.",
        "Myth: It does not change the color or growth rate.",
        "Hack: It can last indefinitely.",
        "Myth: Lightning absolutely can."
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        questionText = findViewById(R.id.questionText)
        feedbackText = findViewById(R.id.feedbackText)
// Giving id to my buttons
        val hackButton = findViewById<Button>(R.id.hackButton)
        val mythButton = findViewById<Button>(R.id.mythButton)
        val nextButton = findViewById<Button>(R.id.nextButton)

        loadQuestion()
// Adding True or False
        hackButton.setOnClickListener { checkAnswer(true) }
        mythButton.setOnClickListener { checkAnswer(false) }

        nextButton.setOnClickListener {
            index++
// Adding the If Else
            if (index < questions.size) {
                loadQuestion()
                feedbackText.text = ""
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    fun loadQuestion() {
        questionText.text = questions[index]
    }

    fun checkAnswer(userAnswer: Boolean) {
        if (userAnswer == answers[index]) {
            feedbackText.text = "Great Job! 🎉\n${explanations[index]}"
            score++
        } else {
            feedbackText.text = "Incorrect! ❌\n${explanations[index]}"
        }
    }
}


