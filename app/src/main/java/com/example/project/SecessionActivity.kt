package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class SecessionActivity : AppCompatActivity() {

    private val TAG = "SecessionActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secession)

    }

    private fun deleteUser() {
        val user = FirebaseAuth.getInstance().currentUser

        user?.delete()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User account deleted.")
                    // 사용자 계정이 성공적으로 삭제된 경우 추가 작업 수행
                    // 예: 로그아웃 처리, UI 업데이트 등
                } else {
                    Log.w(TAG, "Failed to delete user account.", task.exception)
                    // 사용자 계정 삭제 실패 시 추가 작업 수행
                    // 예: 에러 메시지 표시 등
                }
            }
    }
}