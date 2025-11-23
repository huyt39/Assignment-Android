package com.example.playstoreclone

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listViewSuggested: ListView
    private lateinit var horizontalScrollViewRecommended: HorizontalScrollView
    private lateinit var linearLayoutRecommended: LinearLayout
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        listViewSuggested = findViewById(R.id.listViewSuggested)
        horizontalScrollViewRecommended = findViewById(R.id.horizontalScrollViewRecommended)
        linearLayoutRecommended = findViewById(R.id.linearLayoutRecommended)
        
        // tao danh sach app cho section "Suggested for you"
        val suggestedApps = listOf(
            App(
                name = "Mech Assemble: Zombie Swarm",
                category = "Action • Role Playing • Roguelike • Zombie",
                rating = "4.8",
                size = "624 MB",
                iconColor = "#F44336",
                iconText = "M"
            ),
            App(
                name = "MU: Hong Hoa Dao",
                category = "Role Playing",
                rating = "4.8",
                size = "339 MB",
                iconColor = "#795548",
                iconText = "MU"
            ),
            App(
                name = "War Inc: Rising",
                category = "Strategy • Tower defense",
                rating = "4.9",
                size = "231 MB",
                iconColor = "#03A9F4",
                iconText = "W"
            )
        )
        
        val suggestedAdapter = AppAdapter(this, suggestedApps)
        listViewSuggested.adapter = suggestedAdapter
        
        // tao danh sach app cho section "Recommended for you" (horizontal)
        val recommendedApps = listOf(
            App(
                name = "Suno - AI Music &",
                category = "",
                rating = "",
                size = "",
                iconColor = "#FF9800",
                iconText = "S"
            ),
            App(
                name = "Claude by",
                category = "",
                rating = "",
                size = "",
                iconColor = "#8D6E63",
                iconText = "C"
            ),
            App(
                name = "DramaBox -",
                category = "",
                rating = "",
                size = "",
                iconColor = "#F44336",
                iconText = "D"
            ),
            App(
                name = "App 4",
                category = "",
                rating = "",
                size = "",
                iconColor = "#2196F3",
                iconText = "A"
            )
        )
        
        // them cac app vao horizontal layout
        recommendedApps.forEach { app ->
            val appView = layoutInflater.inflate(R.layout.item_app_horizontal, null)
            val textViewName = appView.findViewById<TextView>(R.id.textViewAppName)
            val imageViewIcon = appView.findViewById<TextView>(R.id.imageViewAppIcon)
            
            textViewName.text = app.name
            imageViewIcon.text = app.iconText
            imageViewIcon.setBackgroundColor(Color.parseColor(app.iconColor))
            
            val layoutParams = LinearLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.app_horizontal_width),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            val marginEnd = (16 * resources.displayMetrics.density).toInt()
            layoutParams.setMargins(0, 0, marginEnd, 0)
            appView.layoutParams = layoutParams
            
            linearLayoutRecommended.addView(appView)
        }
    }
}

data class App(
    val name: String,
    val category: String,
    val rating: String,
    val size: String,
    val iconColor: String,
    val iconText: String
)

