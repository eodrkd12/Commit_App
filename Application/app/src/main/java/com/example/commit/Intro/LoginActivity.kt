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

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.commit.Interface.RetrofitNetwork
import com.example.commit.Main.MainActivity
import com.example.commit.R
import com.example.commit.Singleton.VolleyService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Array

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener  {
            var id:String=edit_id.text.toString()
            var pw:String=edit_pw.text.toString()

            VolleyService.loginReq(id,pw,this, {success ->
                when(success){
                    0 -> {
                        //통신 실패
                        Toast.makeText(this,"서버와의 통신에 실패했습니다.",Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        //로그인 실패 : ID not exist
                        Toast.makeText(this,"계정을 확인해주세요.",Toast.LENGTH_SHORT).show()
                    }

                    2 -> {
                        //로그인 실패 : PW error
                        Toast.makeText(this,"ID / PW를 확인해주세요.",Toast.LENGTH_SHORT).show()
                    }
                    3 -> {
                        //로그인 성공
                        var intent:Intent=Intent(this,MainActivity::class.java)
                        intent.putExtra("id",id)
                        intent.putExtra("pw",pw)
                        startActivity(intent)
                    }
                }
            })
        }
    }
}
