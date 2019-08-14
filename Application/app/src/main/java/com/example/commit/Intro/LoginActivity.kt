/*
Login Activity
1. 로고
2. 아이디 입력
3. 비밀번호 입력
4. 로그인 버튼 -> MainActivity
5. 아이디 / 비밀번호 찾기 -> 아이디 / 비번찾기 activity로 전환
6. 어케하노 -> 앱 소개(n개의 GuideActivity)로 전환
 */
package com.example.commit.Intro

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.commit.R
import com.example.commit.Singleton.VolleyService
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener  {
            VolleyService.loginReq(this){success ->
                if(success){
                    Toast.makeText(this,"통신 성공",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"통신 실패",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
