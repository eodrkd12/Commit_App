package com.example.commit.IntroActivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.commit.R
import com.example.commit.Singleton.VolleyService
import kotlinx.android.synthetic.main.activity_join1.*

class Join1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join1)

        btn_university.setOnClickListener {
            var name = edit_university.text.toString()
            VolleyService.search_university(name, this, { success ->

            })
        }
    }
}
