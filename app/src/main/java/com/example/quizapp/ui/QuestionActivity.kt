package com.example.quizapp.ui

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import com.example.quizapp.Constant
import com.example.quizapp.QuestionDataClass
import com.example.quizapp.R
import com.example.quizapp.ResultDataClass
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var countDownTimer: CountDownTimer
    private var list = mutableListOf<QuestionDataClass>()
    private var resultList: ArrayList<ResultDataClass> = ArrayList()
    private var index = 0
    private var currentProgress = 1
    var totalMilliSeconds:Long=30000
    var secondsIncrement:Long=1000
    private var click = 0
    lateinit var binding: ActivityQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addQuestion()
        onClicked()
        setQuestion()
        binding.submit.setOnClickListener {
            countDownTimer.cancel()
            onSubmit()
        }
    }


    private fun onClicked() {
        binding.tvOption1.setOnClickListener(this)
        binding.tvOption2.setOnClickListener(this)
        binding.tvOption3.setOnClickListener(this)
        binding.tvOption4.setOnClickListener(this)
    }
    @SuppressLint("SetTextI18n")
    fun setQuestion() {
        countdown()
        binding.tvProgress.text = "$currentProgress / ${list.size}"
        binding.tvQuestion.text = list.get(index).question
        binding.tvOption1.text = "A-  ${list.get(index).option1}"
        binding.tvOption2.text = "B-  ${list.get(index).option2}"
        binding.tvOption3.text = "C-  ${list.get(index).option3}"
        binding.tvOption4.text = "D-  ${list.get(index).option4}"
    }
    fun onSubmit(){
        if (index < list.size-1) {
            index++
            currentProgress++
            if (click == 1) {
                click--
            }
            else
            {
                resultList.add(ResultDataClass(currentProgress,0))
            }
            binding.tvOption1.setBackgroundResource(R.drawable.options_background)
            binding.tvOption2.setBackgroundResource(R.drawable.options_background)
            binding.tvOption3.setBackgroundResource(R.drawable.options_background)
            binding.tvOption4.setBackgroundResource(R.drawable.options_background)
            setQuestion()
        } else {
            Constant.start++
            val intent = Intent(this, ResultActivity::class.java)
            intent.putParcelableArrayListExtra("ResultList",resultList)
            Log.e("Question ResultList",resultList.toString())
            startActivity(intent)
            this.finish()
        }
    }
    override fun onClick(p0: View?) {
        when (p0) {
            binding.tvOption1 -> {
                selectedOption(binding.tvOption1, list.get(index).option1)
            }
            binding.tvOption2 -> {
                selectedOption(binding.tvOption2, list.get(index).option2)
            }
            binding.tvOption3 -> {
                selectedOption(binding.tvOption3, list.get(index).option3)
            }
            else -> {
                selectedOption(binding.tvOption4, list.get(index).option4)
            }
        }
    }
    private fun selectedOption(view: View, option: String) {
        if (click == 0) {
            if (list.get(index).answer == option) {
                view.setBackgroundResource(R.drawable.right_optionbackground)
                Constant.score++
                click++
                resultList.add(ResultDataClass(currentProgress,1))
            }
            else {
                view.setBackgroundResource(R.drawable.wrong_optionbackground)
                resultList.add(ResultDataClass(currentProgress,-1))
                when (list[index].answer) {
                    list.get(index).option1 -> {
                        binding.tvOption1.setBackgroundResource(R.drawable.right_optionbackground)
                        click++
                    }
                    list[index].option2 -> {
                        binding.tvOption2.setBackgroundResource(R.drawable.right_optionbackground)
                        click++
                    }
                    list[index].option3 -> {
                        binding.tvOption3.setBackgroundResource(R.drawable.right_optionbackground)
                        click++
                    }
                    else -> {
                        binding.tvOption4.setBackgroundResource(R.drawable.right_optionbackground)
                        click++
                    }
                }
            }
        }
    }
    private fun addQuestion() {
        list.add(
            QuestionDataClass(
                "What is a context in android ?",
                "It is an interface to store global information about an application",
                "It is used to create new components.",
                "Android has two contexts, those are getContext() and getApplicationContext()",
                "All of Above",
                "All of Above"
            )
        )
        list.add(
            QuestionDataClass(
                "What is the difference between margin and padding in android layout?",
                "Margin is specifying the extra space left on all four sides in layout",
                "Padding is used to offset the content of a view by specific px or dp",
                "Both A and B are correct",
                "None of the above",
                "Both A and B are correct"
            )
        )
        list.add(
            QuestionDataClass(
                " On which thread broadcast receivers will work in android?",
                "Worker Thread",
                "Main Thread",
                "Activity Thread",
                "None of the Above",
                "Main Thread"
            )
        )

        list.add(
            QuestionDataClass(
                "What is the life cycle of broadcast receivers in android?",
                "send intent()",
                "onRecieve()",
                "implicitBroadcast()",
                "sendBroadcast(), sendOrderBroadcast(), and sendStickyBroadcast()",
                "onRecieve()"
            )
        )

        list.add(
            QuestionDataClass(
                "Persist data can be stored in Android through ?", "Shared Preferences",
                "Internal/External storage",
                "SQlite",
                "All of above",
                "All of above"
            )
        )
        list.add(
            QuestionDataClass(
                "What is a context in android ?",
                "It is an interface to store global information about an application",
                "It is used to create new components.",
                "Android has two contexts, those are getContext() and getApplicationContext()",
                "All of Above",
                "All of Above"
            )
        )
        list.add(
            QuestionDataClass(
                "What is the difference between margin and padding in android layout?",
                "Margin is specifying the extra space left on all four sides in layout",
                "Padding is used to offset the content of a view by specific px or dp",
                "Both A and B are correct",
                "None of the above",
                "Both A and B are correct"
            )
        )
        list.add(
            QuestionDataClass(
                "On which thread broadcast receivers will work in android?",
                "Worker Thread",
                "Main Thread",
                "Activity Thread",
                "None of the Above",
                "Main Thread"
            )
        )

        list.add(
            QuestionDataClass(
                "What is the life cycle of broadcast receivers in android?",
                "send intent()",
                "onRecieve()",
                "implicitBroadcast()",
                "sendBroadcast(), sendOrderBroadcast(), and sendStickyBroadcast()",
                "onRecieve()"
            )
        )

        list.add(
            QuestionDataClass(
                "Persist data can be stored in Android through ?", " Shared Preferences",
                "Internal/External storage",
                "SQlite",
                " All of above",
                "Shared Preferences"
            )
        )
    }
    private fun countdown(){
        countDownTimer= object : CountDownTimer(totalMilliSeconds, secondsIncrement) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {

                var  remainingSeconds=(millisUntilFinished / 1000).toInt()
                updateTimeUI(remainingSeconds)
            }
            override fun onFinish() {
                onSubmit()
            }
        }.start()
    }
    @SuppressLint("SetTextI18n")
    fun updateTimeUI(remainingSeconds: Int) {

        if(remainingSeconds < 10) {
            binding.tvTimer.text = "00:0${remainingSeconds}"
            binding.tvTimer.setTextColor(Color.parseColor("#E8574D"))
        }
        else {
            binding.tvTimer.text = "00:${remainingSeconds}"
            binding.tvTimer.setTextColor(Color.parseColor("#FF000000"))
        }
        binding.progressBar.max = ((totalMilliSeconds/secondsIncrement) - 1 ).toInt()
        ObjectAnimator.ofInt(binding.progressBar, "Progress",remainingSeconds).start()
    }
}