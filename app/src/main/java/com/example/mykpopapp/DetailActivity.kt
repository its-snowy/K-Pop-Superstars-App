package com.example.mykpopapp

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_KPOP = "extra_KPOP"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)

        val kpopSuperstars = intent.getParcelableExtra<KpopSuperstars>(EXTRA_KPOP)

        kpopSuperstars?.let { stars ->
            val tvDetailTitle: TextView = findViewById(R.id.tv_detail_title)
            val ivDetailImage: ImageView = findViewById(R.id.iv_detail_image)
            val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
            val tvDetailDebutYear: TextView = findViewById(R.id.tv_detail_debut_year)
            val tvDetailGenre: TextView = findViewById(R.id.tv_detail_genre)
            val tvDetailFanClub: TextView = findViewById(R.id.tv_detail_fan_club)

            tvDetailTitle.text = stars.name
            ivDetailImage.setImageResource(stars.photo)
            tvDetailDescription.text = stars.description
            tvDetailDebutYear.text = stars.debutYear.toString()
            tvDetailGenre.text = stars.genre
            tvDetailFanClub.text = stars.fanClubName

            val shareButton: ImageButton = findViewById(R.id.action_share)
            shareButton.setOnClickListener {
                val shareText = "Check out this KPOP superstar: ${stars.name}"
                ShareCompat.IntentBuilder(this)
                    .setType("text/plain")
                    .setText(shareText)
                    .startChooser()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}