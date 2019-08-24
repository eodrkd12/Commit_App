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
        val url = "http://172.30.1.42:3000/user/login"

        val json = JSONObject()
        json.put("id", id)

        var jsonArray: JSONArray = JSONArray()
        jsonArray.put(json)

        var request = object : JsonObjectRequest(Method.POST
            , url
            , json
            , Response.Listener {
                if (pw != it.getString("PW"))
                    success(2)
                else if (pw == it.getString("PW"))
                    success(3)
            }
            , Response.ErrorListener {
                if (it is com.android.volley.TimeoutError) {
                    Log.d("test", "TimeoutError")
                    success(0)
                } else if (it is com.android.volley.ParseError) {
                    Log.d("test", "ParserError")
                    success(1)
                }
            }
        ) {
            override fun getBodyContentType(): String {
                return "application/json"
            }
        }


        Volley.newRequestQueue(context).add(request)
    }

    //이메일로 아이디 찾기
}
