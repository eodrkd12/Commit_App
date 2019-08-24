/*
AcFindActivity
1. (ID 찾는 버튼)이메일 입력 및 확인 -> (팝업)ID 확인가능
2. (P/W 찾는 버튼) ID/이메일 입력 -> 이메일로 인증번호 보냄
                                -> (새로운 엑티비티 전환 후) -> 인증번호 입력 후 확인  -> 새로운 비밀번호 입력 및 확인 -> LoginActivity 로 이동

*/
package com.example.commit.Intro

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.commit.R

class AcFindActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acfind) //엑티비티파인드
    }

    button.setOnClickListener{
        var email:String = editText.text.toString()
    }
}