package com.example.commit.MainActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.commit.Adapter.DatingAdapter
import com.example.commit.R
import com.example.commit.Singleton.VolleyService
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.activity_join1.*
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class ContentActivity : AppCompatActivity() {

    var datingAdapter=DatingAdapter()
    var datingArray:JSONArray?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        var intent=intent
        var tag=intent.getStringExtra("tag")

        when(tag){
            "DATING" -> {
                VolleyService.datingUserReq(this,{success ->
                    list_content.adapter=datingAdapter
                    datingAdapter.clear()

                    datingArray=success

                    if(datingArray!!.length()==0){

                    }
                    else{
                        for(i in 0..datingArray!!.length()-1){
                            var json=JSONObject()
                            json=datingArray!![i] as JSONObject
                            var nickname=json.getString("nickname")
                            var department=json.getString("department")
                            //현재 연도 구하기
                            var calendar=GregorianCalendar(Locale.KOREA)
                            var year=calendar.get(Calendar.YEAR)
                            //이용자 생일 구하기
                            var birthday=json.getString("birthday")
                            birthday=birthday.substring(0,4)

                            //이용자 나이 계산
                            var age=year-Integer.parseInt(birthday)+1
                            Log.d("test","${birthday}")
                            //이용자 성별
                            var gender:String?=null;
                            if(json.getString("gender")=="M")
                                gender="남자"
                            else
                                gender="여자"
                            datingAdapter.addItem(nickname,department,age,gender)
                        }
                    }

                    datingAdapter.notifyDataSetChanged()
                })
            }
        }
    }
}
