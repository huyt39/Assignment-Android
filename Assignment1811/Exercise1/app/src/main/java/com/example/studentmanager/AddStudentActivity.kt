package com.example.studentmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {
    private lateinit var editTextMSSV: EditText
    private lateinit var editTextHoTen: EditText
    private lateinit var editTextSoDienThoai: EditText
    private lateinit var editTextDiaChi: EditText
    private lateinit var buttonAdd: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        
        // khoi tao cac view
        editTextMSSV = findViewById(R.id.editTextMSSV)
        editTextHoTen = findViewById(R.id.editTextHoTen)
        editTextSoDienThoai = findViewById(R.id.editTextSoDienThoai)
        editTextDiaChi = findViewById(R.id.editTextDiaChi)
        buttonAdd = findViewById(R.id.buttonAdd)
        
        // xu ly nut add
        buttonAdd.setOnClickListener {
            val mssv = editTextMSSV.text.toString().trim()
            val hoTen = editTextHoTen.text.toString().trim()
            val soDienThoai = editTextSoDienThoai.text.toString().trim()
            val diaChi = editTextDiaChi.text.toString().trim()
            
            if (mssv.isEmpty() || hoTen.isEmpty() || soDienThoai.isEmpty() || diaChi.isEmpty()) {
                Toast.makeText(this, "vui long nhap day du thong tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            val student = Student(mssv, hoTen, soDienThoai, diaChi)
            
            if (StudentManager.addStudent(student)) {
                Toast.makeText(this, "da them sinh vien thanh cong", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "mssv da ton tai", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

