package com.example.gmailclone

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class EmailAdapter(
    private val activity: MainActivity,
    private val emails: List<Email>
) : BaseAdapter() {
    
    override fun getCount(): Int = emails.size
    
    override fun getItem(position: Int): Email = emails[position]
    
    override fun getItemId(position: Int): Long = position.toLong()
    
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(activity)
            .inflate(R.layout.item_email, parent, false)
        
        val email = emails[position]
        
        val textViewSender = view.findViewById<TextView>(R.id.textViewSender)
        val textViewSubject = view.findViewById<TextView>(R.id.textViewSubject)
        val textViewPreview = view.findViewById<TextView>(R.id.textViewPreview)
        val textViewTime = view.findViewById<TextView>(R.id.textViewTime)
        val imageViewAvatar = view.findViewById<TextView>(R.id.imageViewAvatar)
        val imageViewStar = view.findViewById<ImageView>(R.id.imageViewStar)
        
        textViewSender.text = email.sender
        textViewSubject.text = email.subject
        textViewPreview.text = email.preview
        textViewTime.text = email.time
        
        // tao avatar mau
        imageViewAvatar.text = email.avatarText
        imageViewAvatar.setBackgroundColor(Color.parseColor(email.avatarColor))
        
        return view
    }
}

