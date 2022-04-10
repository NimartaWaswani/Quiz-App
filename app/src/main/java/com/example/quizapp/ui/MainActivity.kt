package com.example.quizapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.quizapp.Constant
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when(Constant.start){
            0->{
                binding.tvTitle.text=getString(R.string.welcome)
                binding.tvSubtitle.text=getString(R.string.enter_name)
                binding.tfName.visibility= View.VISIBLE
                binding.btn.text=getString(R.string.next)
                binding.btn.setOnClickListener{
                    if(binding.tvName.text.isNullOrEmpty()){
                        binding.tvName.error=getString(R.string.enterName_error)
                    }
                    else
                    {
                        Constant.name=binding.tvName.text.toString()
                        val intent= Intent(this,QuestionActivity::class.java)
                        startActivity(intent)
                        this.finish()
                    }
                }
            }
            1->{
                binding.tvTitle.text="Dear ${Constant.name}!"
                binding.tvSubtitle.text=getString(R.string.your_score)
                binding.tvScore.visibility= View.VISIBLE
                binding.tvScore.text="${Constant.score}"
                binding.btn.text=getString(R.string.finish)
                binding.btn.setOnClickListener {
                    Constant.start--
                    Constant.score=0
                    val intent= Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    this.finish()
                }
            }
        }
    }
}