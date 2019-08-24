/*
UniversityActivity
1. 약관동의 체크
2. 웹메일 인증
3. 인증버튼 -> (완료시)JoinActivity로 전환
 */
package com.example.commit.Intro

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.commit.R
import kotlinx.android.synthetic.main.activity_join.*

class UniversityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_university)

        btn_confirm.setOnClickListener {
            var intent: Intent = Intent(this,JoinActivity::class.java)
            startActivity(intent)
        }
    }
}