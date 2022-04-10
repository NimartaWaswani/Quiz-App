package com.example.quizapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizapp.RecylerView
import com.example.quizapp.ResultDataClass
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.databinding.ActivityQuestionBinding
import com.example.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


      var  list = intent.getParcelableArrayListExtra<ResultDataClass>("ResultList")
        val adapter= list?.let { RecylerView(it,this) }
        binding.recyclerView.adapter=adapter
        binding.button.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}