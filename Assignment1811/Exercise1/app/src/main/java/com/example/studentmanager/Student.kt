package com.example.studentmanager

import java.io.Serializable

data class Student(
    val mssv: String,
    var hoTen: String,
    var soDienThoai: String,
    var diaChi: String
) : Serializable

