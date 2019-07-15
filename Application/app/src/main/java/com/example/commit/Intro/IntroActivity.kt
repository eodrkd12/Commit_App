/*
Intro Activity
1. 로고
2. 회원가입 버튼 회원가입 화면으로 전환(JoinActivity)
3. 로그인 버튼 -> 로그인 화면으로 전환(LoginActivity)
 */
package com.example.commit.Intro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.commit.R

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
