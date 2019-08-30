package com.example.commit.IntroActivity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.commit.Class.GMailSender
import com.example.commit.R
import com.example.commit.Singleton.VolleyService
import kotlinx.android.synthetic.main.activity_join2.*

class Join2Activity : AppCompatActivity() {

    var code:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join2)

        btn_email.setOnClickListener {
            var email: String = edit_email.text.toString()

            if (email.contains("@", true)) {
                //@가 있는 경우
                VolleyService.codeReq(this, { success ->
                    code=success

                    //이메일로 인증번호 보내기
                    var mailSender: GMailSender = GMailSender("eodrkd12@gmail.com", "ioioko123!", code)
                    mailSender.sendMail(
                        "Uniting 이메일 인증"
                        , "안녕하세요.\n" +
                                "아래 인증 코드를 애플리케이션에서 입력하여 회원가입을 진행해주십시오.\n" +
                                "인증코드 : [${code}]\n" +
                                "감사합니다."
                        , edit_email.text.toString()
                    )

                    Toast.makeText(this,"인증번호를 입력해주세요.",Toast.LENGTH_SHORT).show()
                })
            } else {
                Toast.makeText(this, "올바른 이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        btn_code.setOnClickListener{
            if (code==edit_code.text.toString()){
                var intent=Intent(this,Join4Activity::class.java)
                code="만료"
                startActivity(intent)
            }
            else if(code=="만료"){
                Toast.makeText(this,"인증번호를 다시 전송해주세요.",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"인증번호를 확인해주세요",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
