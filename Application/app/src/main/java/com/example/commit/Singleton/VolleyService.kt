package com.example.commit.Singleton

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

//VolleyService를 사용하기위한 싱글톤

object VolleyService {

    //로그인 요청
    fun loginReq(id:String, pw:String,context: Context, success: (Boolean) -> Unit) {
        val url="http://172.30.107.127:3000/user/login"

        val myJson = JSONObject()
        myJson.put("id",id)
        val requestBody = myJson.toString()

        val request=object:StringRequest(Method.GET,
            url,
            Response.Listener{response ->
                Log.d("test","서버 Response 수신: $response")
                success(true)
            },
            Response.ErrorListener { error ->
                Log.d("test","서버 Response 가져오기 실패 : $error")
                success(false)
            }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        Volley.newRequestQueue(context).add(request)
    }
}