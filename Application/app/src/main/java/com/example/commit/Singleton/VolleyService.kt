package com.example.commit.Singleton

import android.content.Context
import android.util.Log
import com.android.volley.ParseError
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.ResponseDelivery
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
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
    //이메일로 아이디 찾기 ->상원

    fun findReq(email:String,context: Context,success: (Int) -> Unit) {
        val url = "http://192.168.25.2:3000/user"

        val json_search = JSONObject()
        json_search.put("email", email)

        var jsonArray: JSONArray = JSONArray()
        jsonArray.put(json_search)

        var request = object : JsonObjectRequest(Method.POST
            , url
            , json_search
            , Response.Listener {
                if (email != it.getString("email"))
                    success(1)
                else if (email == it.getString("email"))
                    success(2)
            }
            , Response.ErrorListener {
                if (it is com.android.volley.TimeoutError) {
                    Log.d("test", "TimeoutError")
                    success(0)
                }
            })
        {
            override fun getBodyContentType(): String {
                return "applycation/json_search"
            }
        }

    } //ID만 찾을떄쓰는 함수

    fun findReq2(id: String, email: String, context: Context, success: (Int) -> Unit){
        val url = "http://192.168.25.2:3000/user"

        val json_search2 = JSONObject()
        json_search2.put("id",id)

        var jsonArray : JSONArray = JSONArray()
        jsonArray.put(json_search2)

        var request = object : JsonObjectRequest(Method.POST
            , url
            , json_search2
            , Response.Listener {
                if(email != it.getString("email"))
                    success(2)
                else if(email == it.getString("email"))
                    success(3)
            }
            ,Response.ErrorListener {
                if(it is com.android.volley.TimeoutError){
                    Log.d("test","TimeoutError")
                    success(0)
                }
                else if(it is com.android.volley.ParseError){
                    Log.d("test","ParseError")
                    success(1)
                }
            })
        {
            override fun getBodyContentType(): String {
                return "applycation/json_search2"
            }
        }

    }//비번 찾을때 함수

    fun check_num(number: Int, context: Context, success: (Int) -> Unit){
        val url = "http://192.168.25.2:3000/user"

        val json_num =JSONObject()
        json_num.put("number",number)

        var jsonArray: JSONArray = JSONArray()
        jsonArray.put(json_num)

        var request = object : JsonObjectRequest(Method.POST
            , url
            , json_num
            , Response.Listener {
                if(number == it.getInt("number"))
                    success(3)
            }
            ,Response.ErrorListener {
                if(it is com.android.volley.TimeoutError){
                    Log.d("test","TimeoutError")
                    success(0)
                }
                else if(it is com.android.volley.ParseError){
                    Log.d("test","ParseError")
                    success(1)
                }
            })
        {
            override fun getBodyContentType(): String {
                return "application/json_num"
            }
        }
    }

    fun change_pw(pw:String, pw2:String, context: Context,success: (Int) -> Unit){
        val url = "http://192.168.25.2:3000/user"

        val json_ch = JSONObject()
        json_ch.put("pw",pw)

        var jsonArray : JSONArray = JSONArray()
        jsonArray.put(json_ch)

        var request = object : JsonObjectRequest(Method.POST
            , url
            , json_ch
            , Response.Listener {
                if(pw != pw2){
                    success(2)
                }
                else if(pw == pw2){
                    success(3)
                }
            }
            , Response.ErrorListener {
                if(it is com.android.volley.TimeoutError){
                    Log.d("test","TimeoutError")
                    success(0)
                }
                else if(it is com.android.volley.ParseError){
                    Log.d("test", "ParseError")
                    success(1)
                }
            })
        {
            override fun getBodyContentType(): String {
                return "application/json_ch"
            }
        }
    }
}
