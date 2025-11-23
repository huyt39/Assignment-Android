package com.example.gmailclone

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listViewEmails: ListView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        listViewEmails = findViewById(R.id.listViewEmails)
        
        // tao danh sach email mau
        val emailList = listOf(
            Email(
                sender = "Nguyen Van A",
                subject = "Hop nhom du an Android - Thu 6 tuan nay",
                preview = "Xin chao moi nguoi, minh muon hen gap de ban ve du an...",
                time = "12:34 PM",
                avatarColor = "#2196F3",
                avatarText = "N"
            ),
            Email(
                sender = "Tran Thi B",
                subject = "Tai lieu hoc tap mon Lap trinh di dong",
                preview = "Gui ban tai lieu on tap cho ky thi cuoi ky, mong ban...",
                time = "11:22 AM",
                avatarColor = "#FF9800",
                avatarText = "T"
            ),
            Email(
                sender = "Le Van C",
                subject = "Xac nhan don hang #12345",
                preview = "Cam on ban da dat hang tai cua hang chung toi. Don hang...",
                time = "11:04 AM",
                avatarColor = "#4CAF50",
                avatarText = "L"
            ),
            Email(
                sender = "Pham Thi D",
                subject = "Lich hen phong van - Vi tri Android Developer",
                preview = "Xin chao, chung toi muon hen ban phong van vao ngay...",
                time = "10:26 AM",
                avatarColor = "#9E9E9E",
                avatarText = "P"
            ),
            Email(
                sender = "Hoang Van E",
                subject = "Cap nhat tinh trang du an",
                preview = "Gui ban bao cao tien do du an trong tuan vua qua. Chung ta...",
                time = "9:45 AM",
                avatarColor = "#8BC34A",
                avatarText = "H"
            ),
            Email(
                sender = "Vu Thi F",
                subject = "Loi moi tham gia su kien Tech Talk",
                preview = "Ban co muon tham gia su kien Tech Talk ve Android khong? Su kien...",
                time = "8:30 AM",
                avatarColor = "#E91E63",
                avatarText = "V"
            )
        )
        
        val adapter = EmailAdapter(this, emailList)
        listViewEmails.adapter = adapter
    }
}

data class Email(
    val sender: String,
    val subject: String,
    val preview: String,
    val time: String,
    val avatarColor: String,
    val avatarText: String
)

