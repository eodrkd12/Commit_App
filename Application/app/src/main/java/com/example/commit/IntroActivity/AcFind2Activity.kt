package com.example.commit.IntroActivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.commit.R
import com.example.commit.Singleton.VolleyService
import kotlinx.android.synthetic.main.activity_acfind2.*

class AcFind2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acfind2)

        var code = intent.getStringExtra("code")

        button_num.setOnClickListener {
            if (code == editText4.text.toString()){
                var intent=Intent(this,AcFind3Activity::class.java)
                code="만료"
                startActivity(intent)
            }
            else if(code == "만료"){
                Toast.makeText(this,"인증번호를 다시 전송해주세요",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"인증번호를 확인해주세요",Toast.LENGTH_SHORT).show()
            }
        } //인증번호
    }
}