package com.example.studentmanager

object StudentManager {
    private val studentList = mutableListOf<Student>()
    
    fun getAllStudents(): List<Student> = studentList.toList()
    
    fun addStudent(student: Student): Boolean {
        // kiem tra mssv da ton tai chua
        if (studentList.any { it.mssv == student.mssv }) {
            return false
        }
        studentList.add(student)
        return true
    }
    
    fun updateStudent(mssv: String, student: Student): Boolean {
        val index = studentList.indexOfFirst { it.mssv == mssv }
        if (index == -1) {
            return false
        }
        studentList[index] = student
        return true
    }
    
    fun deleteStudent(mssv: String): Boolean {
        val index = studentList.indexOfFirst { it.mssv == mssv }
        if (index == -1) {
            return false
        }
        studentList.removeAt(index)
        return true
    }
    
    fun getStudentByMssv(mssv: String): Student? {
        return studentList.find { it.mssv == mssv }
    }
}

