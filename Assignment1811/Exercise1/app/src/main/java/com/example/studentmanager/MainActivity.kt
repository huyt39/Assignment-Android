package com.example.studentmanager

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listViewStudents: ListView
    private lateinit var adapter: StudentAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        listViewStudents = findViewById(R.id.listViewStudents)
        
        // khoi tao adapter
        adapter = StudentAdapter(
            this,
            StudentManager.getAllStudents(),
            onItemClick = { position ->
                // mo activity chi tiet sinh vien
                val student = StudentManager.getAllStudents()[position]
                val intent = Intent(this, StudentDetailActivity::class.java)
                intent.putExtra("mssv", student.mssv)
                startActivity(intent)
            },
            onDeleteClick = { position ->
                // xoa sinh vien
                val student = StudentManager.getAllStudents()[position]
                StudentManager.deleteStudent(student.mssv)
                refreshList()
            }
        )
        
        listViewStudents.adapter = adapter
    }
    
    override fun onResume() {
        super.onResume()
        // cap nhat danh sach khi quay lai activity
        refreshList()
    }
    
    private fun refreshList() {
        adapter.updateStudents(StudentManager.getAllStudents())
        adapter.notifyDataSetChanged()
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuAdd -> {
                // mo activity them sinh vien
                val intent = Intent(this, AddStudentActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
