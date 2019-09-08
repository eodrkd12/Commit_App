/*
AcFindActivity
1. (ID 찾는 버튼)이메일 입력 및 확인 -> (팝업)ID 확인가능
2. (P/W 찾는 버튼) ID/이메일 입력 -> 이메일로 인증번호 보냄
                                -> (새로운 엑티비티 전환 후) -> 인증번호 입력 후 확인  -> 새로운 비밀번호 입력 및 확인 -> LoginActivity 로 이동

*/
package com.example.commit.Intro

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.commit.Class.GMailSender
import com.example.commit.R
import com.example.commit.Singleton.VolleyService
import kotlinx.android.synthetic.main.activity_acfind.*

class AcFindActivity : AppCompatActivity() {

    var code:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acfind) //엑티비티파인드

       button_search.setOnClickListener {
           var email:String=editText.text.toString()

           VolleyService.findReq(email,this, {success ->
               when(success){
                   "Time" -> {
                       //통신 실패
                       Toast.makeText(this,"서버와의 통신에 실패했습니다.",Toast.LENGTH_SHORT).show()
                   }
                   "Parse" ->{
                       // 이메일 오류
                       Toast.makeText(this,"email을 확인해주세요",Toast.LENGTH_SHORT).show()
                   }
                    else ->{
                       //email 일치(성공)
                       var dialog=AlertDialog.Builder(this,android.R.style.Theme_DeviceDefault_Light_Dialog)
                       dialog.setTitle("회읜님의 아이디입니다.")
                       dialog.setMessage(success)
                       dialog.setPositiveButton("확인",null)
                   }
               }
           })
       } // 이 함수는 ID만 잊어버렸을때 email입력하여 email로 ID를 보내주는

        button_search_pw.setOnClickListener {
            var id:String=editText2.text.toString()
            var email:String=editText3.text.toString()

            VolleyService.findReq2(id,email,this, {success ->
                when(success){
                    0 -> {
                        Toast.makeText(this,"통신과 연결이 실패했습니다.",Toast.LENGTH_SHORT).show()
                    }
                    1-> {
                        Toast.makeText(this,"ID가 존재 하지 않습니다.",Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        Toast.makeText(this,"email을 다시 확인 해주세요",Toast.LENGTH_SHORT).show()
                    }
                    3 -> {
                        VolleyService.codeReq(this,{ success ->
                            code=success

                            var mailSender: GMailSender = GMailSender("eodrkd12@gmail.com","ioioko123!",code)
                            mailSender.sendMail(
                                    "Uniting 이메일 인증"
                                    ,"안녕하세요.\n" +
                                    "아래 인증 코드를 애플리케이션에서 입력하여 주세요\n" +
                                    "인증코드 : [${code}]\n" +
                                    "감사합니다."
                                    , editText3.text.toString()
                            )
                        })

                        var intent:Intent= Intent(this,AcFind2Activity::class.java)
                        intent.putExtra("id",id)
                        intent.putExtra("email",email)
                        intent.putExtra("code",code)
                        startActivity(intent)
                    }
                }
            })
        } // 이 함수는 pw을 찾기위함 AcFindActivity2로 이동하며 인증번호가 email로 보내어진다.
    }
}