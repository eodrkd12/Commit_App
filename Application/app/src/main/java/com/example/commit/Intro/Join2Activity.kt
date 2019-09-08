package com.example.commit.Intro

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.android.volley.toolbox.Volley
import com.example.commit.R
import com.example.commit.Singleton.VolleyService
import kotlinx.android.synthetic.main.activity_join2.*
import java.util.*

class Join2Activity : AppCompatActivity() {

    var idCheck:Int = 0
    var nicknameCheck:Int =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join2)

        btn_id.setOnClickListener{
            VolleyService.idCheckReq(edit_id.text.toString(),this,{success ->
                idCheck=success
            })
        }

        btn_nickname.setOnClickListener{
            VolleyService.nicknameCheckReq(edit_nickname.text.toString(),this,{success ->
                nicknameCheck=success
            })
        }

        //날짜 입력
        edit_birthday.setOnClickListener {
            var calendar = Calendar.getInstance()
            var year = calendar.get(Calendar.YEAR)
            var month = calendar.get(Calendar.MONTH)
            var day = calendar.get(Calendar.DAY_OF_MONTH)

            var date_listener  = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    edit_birthday.setText("${year}-${month + 1}-${dayOfMonth}")
                }
            }

            var builder = DatePickerDialog(this, date_listener, year, month, day)
            builder.show()
        }

        btn_confirm.setOnClickListener{
            var id=edit_id.text.toString()
            var pw=edit_pw.text.toString()
            var pwCheck=edit_pw_check.text.toString()
            var name=edit_name.text.toString()
            var birthday=edit_birthday.text.toString()
            var nickname=edit_nickname.text.toString()
            var email=edit_email.text.toString()
            var gender=radio_male.isChecked


            if(idCheck==0)
                Toast.makeText(this,"ID를 확인해주세요.",Toast.LENGTH_SHORT).show()
            else if(pw!=pwCheck)
                Toast.makeText(this,"비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show()
            else if(name=="")
                Toast.makeText(this,"이름을 입력해주세요.",Toast.LENGTH_SHORT).show()
            else if(birthday=="")
                Toast.makeText(this,"생일을 입력해주세요",Toast.LENGTH_SHORT).show()
            else if(nicknameCheck==0)
                Toast.makeText(this,"닉네임을 확인해주세요.",Toast.LENGTH_SHORT).show()
            else if(email=="")
                Toast.makeText(this,"이메일을 입력해주세요.",Toast.LENGTH_SHORT).show()
            else if (!email.contains("@", true))
                Toast.makeText(this,"올바른 이메일을 입력해주세요.",Toast.LENGTH_SHORT).show()
            else{
                var intent= Intent(this,Join3Activity::class.java)
                intent.putExtra("id",id)
                intent.putExtra("pw",pw)
                intent.putExtra("name",name)
                intent.putExtra("birthday",birthday)
                intent.putExtra("nickname",nickname)
                intent.putExtra("email",email)
                if(gender)
                    intent.putExtra("gender",'M')
                else
                    intent.putExtra("gender",'W')
                startActivity(intent)
            }
        }
    }
}
