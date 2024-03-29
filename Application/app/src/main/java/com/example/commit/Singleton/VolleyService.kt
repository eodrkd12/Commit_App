package com.example.commit.Singleton

import android.content.Context
import android.util.Log
import android.widget.Toast
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
    //학교
    val ip: String = "http://172.30.104.192"
    //val ip: String = "http://192.168.43.247"

    //==========세현==========
    //아이디 중복체크
    fun idCheckReq(id: String, context: Context, success: (Int) -> Unit) {
        val url = "${ip}:3000/user/check"

        val json = JSONObject()
        json.put("id", id)

        var request = object : JsonObjectRequest(Method.POST
            , url
            , json
            , Response.Listener {
                Toast.makeText(context, "중복된 ID입니다.", Toast.LENGTH_SHORT).show()
                success(0)
            }
            , Response.ErrorListener {
                if (it is com.android.volley.ParseError) {
                    Toast.makeText(context, "사용 가능한 ID입니다.", Toast.LENGTH_SHORT).show()
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

    //닉네임 중복체크
    fun nicknameCheckReq(nickname: String, context: Context, success: (Int) -> Unit) {
        val url = "${ip}:3000/user/check/nickname"

        val json = JSONObject()
        json.put("nickname", nickname)

        var request = object : JsonObjectRequest(Method.POST
            , url
            , json
            , Response.Listener {
                Toast.makeText(context, "중복된 닉네임입니다.", Toast.LENGTH_SHORT).show()
                success(0)
            }
            , Response.ErrorListener {
                if (it is com.android.volley.ParseError) {
                    Toast.makeText(context, "사용 가능한 닉네임입니다.", Toast.LENGTH_SHORT).show()
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

    //이메일 인증 코드 요청
    fun codeReq(context: Context, success: (String) -> Unit) {
        val url = "${ip}:3000/code"//요청 URL

        val json = JSONObject() // 서버로 전송할 json 객체

        var request = object : JsonObjectRequest(Method.GET
            , url
            , json
            , Response.Listener {
                Log.d("test", "코드 생성 ${it.toString()}")
                success(it.getString("code"))
            }
            , Response.ErrorListener {
                Log.d("test", it.toString())
            }
        ) {
            override fun getBodyContentType(): String {
                return "application/json"
            }
        }
        //요청을 보내는 부분
        Volley.newRequestQueue(context).add(request)
    }

    //로그인 요청
    fun loginReq(id: String, pw: String, context: Context, success: (Int) -> Unit) {
        val url = "${ip}:3000/user/login"//요청 URL

        val json = JSONObject() // 서버로 전송할 json 객체
        json.put("id", id) // json 객체에 데이터 삽입, 첫번째 파라미터가 키, 두번째 파라미터가 값

        // Request객체를 생성하여야 함 종류는 다양하지만 여기선 JsonObjectRequest객체를 생성
        // 객체 생성 파라미터(메소드타입(GET,POST,PUT,DELETE) / URL / 보낼 데이터(json) / 통신 성공 리스너 / 통신 실패 리스너
        var request = object : JsonObjectRequest(Method.POST
            , url
            , json
            , Response.Listener {
                // 통신 성공 리스너 : 통신 성공 시에 호출
                if (pw != it.getString("PW"))
                    success(2)
                else if (pw == it.getString("PW"))
                    success(3)
            }
            , Response.ErrorListener {
                // 통신 실패 리스너 : 통신 실패 시에 호출
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

    //회원가입 요청
    //
    fun joinReq(
        id: String, pw: String, name: String, birthday: String, gender: String
        , nickname: String, webMail: String, universityName: String, departmentName: String, enterYear: String
        , context: Context, success: (Int) -> Unit
    ) {
        val url = "${ip}:3000/user"//요청 URL

        val json = JSONObject() // 서버로 전송할 json 객체
        json.put("id", id) // json 객체에 데이터 삽입, 첫번째 파라미터가 키, 두번째 파라미터가 값
        json.put("pw",pw)
        json.put("name",name)
        json.put("birthday",birthday)
        json.put("gender",gender)
        json.put("nickname",nickname)
        json.put("web_mail",webMail)
        json.put("university_name",universityName)
        json.put("department_name",departmentName)
        json.put("enter_year",enterYear)

        // Request객체를 생성하여야 함 종류는 다양하지만 여기선 JsonObjectRequest객체를 생성
        // 객체 생성 파라미터(메소드타입(GET,POST,PUT,DELETE) / URL / 보낼 데이터(json) / 통신 성공 리스너 / 통신 실패 리스너
        var request = object : JsonObjectRequest(Method.POST
            , url
            , json
            , Response.Listener {
                // 통신 성공 리스너 : 통신 성공 시에 호출
                success(1)
            }
            , Response.ErrorListener {
                // 통신 실패 리스너 : 통신 실패 시에 호출
                Log.d("test",it.toString())
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

    //학교 검색
    fun search_university(name: String, context: Context, success: (JSONArray?) -> Unit) {
        val url = "${ip}:3000/university"

        var jsonObject = JSONObject()
        jsonObject.put("name", name)

        var jsonArray: JSONArray = JSONArray()
        jsonArray.put(jsonObject)

        var request = object : JsonArrayRequest(Method.POST
            , url
            , jsonArray
            , Response.Listener {
                success(it)
            }
            , Response.ErrorListener {
                Log.d("test", it.toString())
            }) {

        }
        Volley.newRequestQueue(context).add(request)
    }

    //학과 검색
    fun search_department(
        universityName: String,
        departmentName: String,
        context: Context,
        success: (JSONArray?) -> Unit
    ) {
        val url = "${ip}:3000/department"

        var jsonObject = JSONObject()
        jsonObject.put("university_name", universityName)
        jsonObject.put("department_name", departmentName)

        var jsonArray: JSONArray = JSONArray()
        jsonArray.put(jsonObject)

        var request = object : JsonArrayRequest(Method.POST
            , url
            , jsonArray
            , Response.Listener {
                success(it)
            }
            , Response.ErrorListener {
                Log.d("test", it.toString())
            }) {

        }
        Volley.newRequestQueue(context).add(request)
    }

    //데이팅 유저 불러오기
    //현재 : 모든 유저 불러오기(임시)
    //계획 : 데이팅 ON 유저만 불러오기
    fun datingUserReq(context:Context,success:(JSONArray?)->Unit){
        val url="${ip}:3000/user/dating"

        var jsonArray=JSONArray()

        var request=object:JsonArrayRequest(
            Method.GET,
            url,
            jsonArray,
            Response.Listener{
                success(it)
            },
            Response.ErrorListener{
                Log.d("test",it.toString())
            }){

        }
        Volley.newRequestQueue(context).add(request)
    }

    //게시글 불러오기 : 태그 이용
    fun postReq(tag:String, context: Context, success: (JSONArray?)->Unit){
        val url="${ip}:3000/post/${tag}"

        val jsonArray=JSONArray()

        var request=object:JsonArrayRequest(
            Method.GET,
            url,
            jsonArray,
            Response.Listener{
                success(it)
            },
            Response.ErrorListener{
                Log.d("test",it.toString())
            }){

        }
        Volley.newRequestQueue(context).add(request)
    }
    //==========세현==========


    //이메일로 아이디 찾기 ->상원
    fun findReq(email: String, context: Context, success: (Int) -> Unit) {
        val url = "${ip}:3000/user"

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
            }) {
            override fun getBodyContentType(): String {
                return "applycation/json_search"
            }
        }

    } //ID만 찾을떄쓰는 함수

    fun findReq2(id: String, email: String, context: Context, success: (Int) -> Unit) {
        val url = "${ip}:3000/user"

        val json_search2 = JSONObject()
        json_search2.put("id", id)

        var jsonArray: JSONArray = JSONArray()
        jsonArray.put(json_search2)

        var request = object : JsonObjectRequest(Method.POST
            , url
            , json_search2
            , Response.Listener {
                if (email != it.getString("email"))
                    success(2)
                else if (email == it.getString("email"))
                    success(3)
            }
            , Response.ErrorListener {
                if (it is com.android.volley.TimeoutError) {
                    Log.d("test", "TimeoutError")
                    success(0)
                } else if (it is com.android.volley.ParseError) {
                    Log.d("test", "ParseError")
                    success(1)
                }
            }) {
            override fun getBodyContentType(): String {
                return "applycation/json_search2"
            }
        }

    }//비번 찾을때 함수

    fun check_num(number: Int, context: Context, success: (Int) -> Unit) {
        val url = "${ip}:3000/user"

        val json_num = JSONObject()
        json_num.put("number", number)

        var jsonArray: JSONArray = JSONArray()
        jsonArray.put(json_num)

        var request = object : JsonObjectRequest(Method.POST
            , url
            , json_num
            , Response.Listener {
                if (number == it.getInt("number"))
                    success(3)
            }
            , Response.ErrorListener {
                if (it is com.android.volley.TimeoutError) {
                    Log.d("test", "TimeoutError")
                    success(0)
                } else if (it is com.android.volley.ParseError) {
                    Log.d("test", "ParseError")
                    success(1)
                }
            }) {
            override fun getBodyContentType(): String {
                return "application/json_num"
            }
        }
    }

    fun change_pw(pw: String, pw2: String, context: Context, success: (Int) -> Unit) {
        val url = "${ip}:3000/user"

        val json_ch = JSONObject()
        json_ch.put("pw", pw)

        var request = object : JsonObjectRequest(Method.POST
            , url
            , json_ch
            , Response.Listener {
                if (pw != pw2) {
                    success(2)
                } else if (pw == pw2) {
                    success(3)
                }
            }
            , Response.ErrorListener {
                if (it is com.android.volley.TimeoutError) {
                    Log.d("test", "TimeoutError")
                    success(0)
                } else if (it is com.android.volley.ParseError) {
                    Log.d("test", "ParseError")
                    success(1)
                }
            }) {
            override fun getBodyContentType(): String {
                return "application/json_ch"
            }
        }
    }


}
