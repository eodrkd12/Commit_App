package com.example.commit.Intro

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.commit.R
import com.example.commit.Singleton.VolleyService
import kotlinx.android.synthetic.main.activity_acfind.*
import kotlinx.android.synthetic.main.activity_acfind2.*
import kotlinx.android.synthetic.main.activity_acfind3.*
import kotlinx.android.synthetic.main.activity_login.view.*

class AcFind3Activity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acfind3)

        button_check2.setOnClickListener {
            if(editText7.text.toString()==editText8.text.toString()){
                VolleyService.

                //재설정한 비밀번호를 서버에 덮어씌우기
                var intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"비밀번호가 다릅니다 다시확인 해주세요",Toast.LENGTH_SHORT).show()
            }
        }

    }
}