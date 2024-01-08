package com.example.project


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.view.View

class LoadingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)


        val intent = Intent(this, MainActivity::class.java)

        findViewById<View>(android.R.id.content).postDelayed({
            startActivity(intent)
            finish()
        }, 3000)
    }

    override fun onPause() {
        super.onPause()
        // 스플래쉬 화면에서 벗어나면 핸들러에 대기 중인 작업을 취소하여 메모리 누수를 방지합니다.
        // 만약에 이동하기 전에 뒤로 가기 버튼을 누른다면, 스플래쉬 화면으로 돌아가지 않게 됩니다.
        finish()
    }
}