package com.example.studentmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView

class StudentAdapter(
    private val activity: MainActivity,
    private val students: List<MainActivity.Student>,
    private val onItemClick: (Int) -> Unit,
    private val onDeleteClick: (Int) -> Unit
) : BaseAdapter() {
    
    override fun getCount(): Int = students.size
    
    override fun getItem(position: Int): MainActivity.Student = students[position]
    
    override fun getItemId(position: Int): Long = position.toLong()
    
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(activity)
            .inflate(R.layout.item_student, parent, false)
        
        val student = students[position]
        
        val textViewHoTen = view.findViewById<TextView>(R.id.textViewHoTen)
        val textViewMSSV = view.findViewById<TextView>(R.id.textViewMSSV)
        val buttonDelete = view.findViewById<ImageButton>(R.id.buttonDelete)
        
        textViewHoTen.text = student.hoTen
        textViewMSSV.text = student.mssv
        
        // xu ly khi nhan vao item
        view.setOnClickListener {
            onItemClick(position)
        }
        
        // xu ly khi nhan nut xoa
        buttonDelete.setOnClickListener {
            onDeleteClick(position)
        }
        
        return view
    }
}

