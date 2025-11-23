package com.example.studentmanager

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editTextMSSV: EditText
    private lateinit var editTextHoTen: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonUpdate: Button
    private lateinit var listViewStudents: ListView
    
    private val studentList = mutableListOf<Student>()
    private lateinit var adapter: StudentAdapter
    private var selectedPosition = -1
    
    data class Student(
        val mssv: String,
        var hoTen: String
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // khoi tao cac view
        editTextMSSV = findViewById(R.id.editTextMSSV)
        editTextHoTen = findViewById(R.id.editTextHoTen)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonUpdate = findViewById(R.id.buttonUpdate)
        listViewStudents = findViewById(R.id.listViewStudents)
        
        // khoi tao adapter
        adapter = StudentAdapter(
            this,
            studentList,
            onItemClick = { position ->
                // xu ly khi nhan vao mot phan tu trong danh sach
                selectedPosition = position
                val student = studentList[position]
                editTextMSSV.setText(student.mssv)
                editTextHoTen.setText(student.hoTen)
            },
            onDeleteClick = { position ->
                // xu ly khi nhan nut xoa
                studentList.removeAt(position)
                adapter.notifyDataSetChanged()
                // xoa thong tin trong edittext neu dang chon phan tu nay
                if (selectedPosition == position) {
                    selectedPosition = -1
                    editTextMSSV.setText("")
                    editTextHoTen.setText("")
                } else if (selectedPosition > position) {
                    selectedPosition--
                }
            }
        )
        
        listViewStudents.adapter = adapter
        
        // xu ly nut add
        buttonAdd.setOnClickListener {
            val mssv = editTextMSSV.text.toString().trim()
            val hoTen = editTextHoTen.text.toString().trim()
            
            if (mssv.isEmpty() || hoTen.isEmpty()) {
                Toast.makeText(this, "vui long nhap day du thong tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            // kiem tra mssv da ton tai chua
            if (studentList.any { it.mssv == mssv }) {
                Toast.makeText(this, "mssv da ton tai", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            studentList.add(Student(mssv, hoTen))
            adapter.notifyDataSetChanged()
            
            // xoa du lieu trong edittext
            editTextMSSV.setText("")
            editTextHoTen.setText("")
            selectedPosition = -1
            
            Toast.makeText(this, "da them sinh vien thanh cong", Toast.LENGTH_SHORT).show()
        }
        
        // xu ly nut update
        buttonUpdate.setOnClickListener {
            if (selectedPosition == -1) {
                Toast.makeText(this, "vui long chon sinh vien can cap nhat", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            val hoTen = editTextHoTen.text.toString().trim()
            
            if (hoTen.isEmpty()) {
                Toast.makeText(this, "vui long nhap ho ten", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            studentList[selectedPosition].hoTen = hoTen
            adapter.notifyDataSetChanged()
            
            Toast.makeText(this, "da cap nhat thanh cong", Toast.LENGTH_SHORT).show()
        }
    }
}

