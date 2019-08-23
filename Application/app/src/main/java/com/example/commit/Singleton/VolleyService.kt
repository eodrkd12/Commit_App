package com.example.commit.Singleton

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject

//VolleyService를 사용하기위한 싱글톤

object VolleyService {

    //로그인 요청
    fun loginReq(id: String, pw: String, context: Context, success: (Int) -> Unit) {
        val url = "http://192.168.0.21:3000/user/login"//요청 URL

        val json = JSONObject() // 서버로 전송할 json 객체
        json.put("id", id) // json 객체에 데이터 삽입, 첫번째 파라미터가 키, 두번째 파라미터가 값

        // Request객체를 생성하여야 함 종류는 다양하지만 여기선 JsonObjectRequest객체를 생성
        // 객체 생성 파라미터(메소드타입(GET,POST,PUT,DELETE) / URL / 보낼 데이터(json) / 통신 성공 리스너 / 통신 실패 리스너
        var request = object : JsonObjectRequest(Method.POST
            , url
            , json
            , Response.Listener { // 통신 성공 리스너 : 통신 성공 시에 호출
                if (pw != it.getString("PW"))
                    success(2)
                else if (pw == it.getString("PW"))
                    success(3)
            }
            , Response.ErrorListener { // 통신 실패 리스너 : 통신 실패 시에 호출
                if (it is com.android.volley.TimeoutError) {
                    Log.d("test", "TimeoutError")
                    success(0)
                } else if (it is com.android.volley.ParseError) {
                    Log.d("test", "ParserError")
                    success(1)
                }
            }
        ) {
            //객체 생성 괄호(소괄호)를 닫은 후에 추가하는 요청 Body 부분(비어있어도 됨)
            //getBodyContentType()은 보내는 데이터의 타입을 정의하는 것
            override fun getBodyContentType(): String {
                return "application/json"
            }
        }
        //요청을 보내는 부분
        Volley.newRequestQueue(context).add(request)
    }
}
