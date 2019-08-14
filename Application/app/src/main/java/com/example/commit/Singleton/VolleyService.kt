package com.example.commit.Singleton

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

//VolleyService를 사용하기위한 싱글톤

object VolleyService {

    fun loginReq(context: Context, success: (Boolean) -> Unit) {
        val url="172.30.1.42:3000/user"
        val myJson = JSONObject()
        val requestBody = myJson.toString()

        val request=object:StringRequest(Method.GET,
            url,
            Response.Listener{response ->  
                Log.d("success","서버 Response 수신: $response")
                success(true)
            },
            Response.ErrorListener { error ->
                Log.d("error","서버 Response 가져오기 실패 : $error")
                success(false)
            }){
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