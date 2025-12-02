package com.example.studentmanager

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StudentDetailActivity : AppCompatActivity() {
    private lateinit var editTextMSSV: EditText
    private lateinit var editTextHoTen: EditText
    private lateinit var editTextSoDienThoai: EditText
    private lateinit var editTextDiaChi: EditText
    private lateinit var buttonUpdate: Button
    private lateinit var buttonDelete: Button
    
    private var currentStudent: Student? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)
        
        // lay mssv tu intent
        val mssv = intent.getStringExtra("mssv")
        if (mssv == null) {
            Toast.makeText(this, "khong tim thay sinh vien", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        // lay thong tin sinh vien
        currentStudent = StudentManager.getStudentByMssv(mssv)
        if (currentStudent == null) {
            Toast.makeText(this, "khong tim thay sinh vien", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        
        // khoi tao cac view
        editTextMSSV = findViewById(R.id.editTextMSSV)
        editTextHoTen = findViewById(R.id.editTextHoTen)
        editTextSoDienThoai = findViewById(R.id.editTextSoDienThoai)
        editTextDiaChi = findViewById(R.id.editTextDiaChi)
        buttonUpdate = findViewById(R.id.buttonUpdate)
        buttonDelete = findViewById(R.id.buttonDelete)
        
        // hien thi thong tin sinh vien
        editTextMSSV.setText(currentStudent!!.mssv)
        editTextHoTen.setText(currentStudent!!.hoTen)
        editTextSoDienThoai.setText(currentStudent!!.soDienThoai)
        editTextDiaChi.setText(currentStudent!!.diaChi)
        
        // xu ly nut update
        buttonUpdate.setOnClickListener {
            val mssv = editTextMSSV.text.toString().trim()
            val hoTen = editTextHoTen.text.toString().trim()
            val soDienThoai = editTextSoDienThoai.text.toString().trim()
            val diaChi = editTextDiaChi.text.toString().trim()
            
            if (mssv.isEmpty() || hoTen.isEmpty() || soDienThoai.isEmpty() || diaChi.isEmpty()) {
                Toast.makeText(this, "vui long nhap day du thong tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            // kiem tra neu mssv thay doi va trung voi sinh vien khac
            if (mssv != currentStudent!!.mssv) {
                val existingStudent = StudentManager.getStudentByMssv(mssv)
                if (existingStudent != null) {
                    Toast.makeText(this, "mssv da ton tai", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            
            val updatedStudent = Student(
                mssv,
                hoTen,
                soDienThoai,
                diaChi
            )
            
            val oldMssv = currentStudent!!.mssv
            
            if (StudentManager.updateStudent(oldMssv, updatedStudent)) {
                Toast.makeText(this, "da cap nhat thanh cong", Toast.LENGTH_SHORT).show()
                currentStudent = updatedStudent
            } else {
                Toast.makeText(this, "cap nhat that bai", Toast.LENGTH_SHORT).show()
            }
        }
        
        // xu ly nut delete
        buttonDelete.setOnClickListener {
            if (StudentManager.deleteStudent(currentStudent!!.mssv)) {
                Toast.makeText(this, "da xoa sinh vien thanh cong", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "xoa that bai", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

