package com.example.commit.Intro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.commit.R
import kotlinx.android.synthetic.main.activity_join1.*
import kotlin.random.Random

class Join1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join1)

        btn_email.setOnClickListener{
            var email:String=edit_email.text.toString()

            if(email.contains("@",true)){
                //@가 있는 경우
                var code=generateCode()
            }
            else{
                Toast.makeText(this,"올바른 이메일을 입력해주세요.",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun generateCode():String{
        var rand:Random= Random
        var code:String=""

        for(i in 1..6){
            var num=rand.nextInt(10).toString()
            code+=num
        }
        Log.d("test",code)
        return code;
    }
}
