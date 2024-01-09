package com.example.project

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project.FirebaseData
import com.example.project.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding // 뷰바인딩
    private lateinit var mAuth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater) // 바인딩 위해 선언
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener { // xml을 꾸며줘야 함
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            signUpUser(name, email, password)
        }
    }

    private fun signUpUser(name: String, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser

                    // FirebaseData 객체 생성
                    val userData = FirebaseData(name, email, password)

                    // Firestore에 데이터 추가
                    addUserToFirestore(userData)

                    // 회원가입 성공 시 수행할 작업
                    Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                    // 예: 홈 화면으로 이동
                } else {
                    // 회원가입 실패 시 오류 처리
                    // Toast.makeText(this, "회원가입 실패!", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, "회원가입 실패 : ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun addUserToFirestore(userData: FirebaseData) {
        val userRef = db.collection("users")
        userRef.document(userData.Email)
            .set(userData)
            .addOnSuccessListener {
                // Firestore에 추가 성공 시 원하는 작업 수행
            }
            .addOnFailureListener { e ->
                // Firestore 추가 도중 오류 발생 시 처리
            }
    }
}
