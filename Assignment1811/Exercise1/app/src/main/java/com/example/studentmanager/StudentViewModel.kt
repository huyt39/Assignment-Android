package com.example.studentmanager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    private val _studentList = MutableLiveData<List<Student>>()
    val studentList: LiveData<List<Student>> = _studentList
    
    init {
        loadStudents()
    }
    
    private fun loadStudents() {
        _studentList.value = StudentManager.getAllStudents()
    }
    
    fun addStudent(student: Student): Boolean {
        val result = StudentManager.addStudent(student)
        if (result) {
            loadStudents()
        }
        return result
    }
    
    fun updateStudent(mssv: String, student: Student): Boolean {
        val result = StudentManager.updateStudent(mssv, student)
        if (result) {
            loadStudents()
        }
        return result
    }
    
    fun deleteStudent(mssv: String): Boolean {
        val result = StudentManager.deleteStudent(mssv)
        if (result) {
            loadStudents()
        }
        return result
    }
    
    fun getStudentByMssv(mssv: String): Student? {
        return StudentManager.getStudentByMssv(mssv)
    }
}

