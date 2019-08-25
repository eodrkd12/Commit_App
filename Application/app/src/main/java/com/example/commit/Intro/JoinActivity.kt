/*
JoinActivity
1. 학교명 입력 , 학교검색 -> 드롭박스에서 선택 -> UniversityActivity로 전환 -> 인증후에 인증완료 메시지 출력
*학번인증을 함으로써 학교,학과,학번,이름,성별,나이 획득
2. 로그인할 ID 입력 : 중복체크버튼
3. 비밀번호
4. 비밀번호 확인
5. 이름 입력
6. 성별 입력
7. 나이 입력
8. 닉네임 : 중복체크버튼
9. 이메일 : 아이디/비밀번호 찾기 전용
10. 회원가입 완료 버튼 -> LoginActivity로 전환
*/
package com.example.commit.Intro

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.commit.R
import kotlinx.android.synthetic.main.activity_join.*

class JoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        btn_confirm.setOnClickListener {
            var intent: Intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        btn_university.setOnClickListener {
            var intent: Intent = Intent(this,UniversityActivity::class.java)
            startActivity(intent)
        }

    }
}