package com.example.commit.IntroActivity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.commit.R
import com.example.commit.Singleton.VolleyService
import kotlinx.android.synthetic.main.activity_acfind.*
import kotlinx.android.synthetic.main.activity_acfind2.*
import kotlinx.android.synthetic.main.activity_acfind3.*
import kotlinx.android.synthetic.main.activity_login.view.*

class AcFind3Activity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acfind3)

        var id = intent.getStringExtra("id")
        var pw:String=editText7.text.toString()

        button_check2.setOnClickListener {
            if(editText7.text.toString()==editText8.text.toString()){
                VolleyService.change_pw(id,pw,this,{success ->
                    when(success){
                        0 -> {
                            Toast.makeText(this,"비밀번호변경 되었습니다.",Toast.LENGTH_SHORT).show()
                            var intent:Intent = Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                        }
                        1 -> {
                            Toast.makeText(this,"비밀번호 재설정 해주십시오",Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }

    }
}