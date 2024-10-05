package com.example.mykpopapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvKpopSuperstars: RecyclerView
    private val list = ArrayList<KpopSuperstars>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi Toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        rvKpopSuperstars = findViewById(R.id.rv_kpop_superstars)
        rvKpopSuperstars.setHasFixedSize(true)

        list.addAll(getListKpopSuperstars())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        for (i in 0 until menu.size()) {
            val item = menu.getItem(i)
            item.title?.let { title ->
                val spannableString = SpannableString(title)
                spannableString.setSpan(
                    ForegroundColorSpan(Color.BLACK),
                    0,
                    spannableString.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                item.title = spannableString
            }
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListKpopSuperstars(): ArrayList<KpopSuperstars> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataDebutYear = resources.getIntArray(R.array.data_debut_year)
        val dataFanClub = resources.getStringArray(R.array.data_fan_club)
        val dataGenre = resources.getStringArray(R.array.data_genre)

        val listKpop = ArrayList<KpopSuperstars>()
        for (i in dataName.indices) {
            val kpop = KpopSuperstars(
                name = dataName[i],
                description = dataDescription[i],
                photo = dataPhoto.getResourceId(i, -1),
                debutYear = dataDebutYear[i],
                fanClubName = dataFanClub[i],
                genre = dataGenre[i]
            )
            listKpop.add(kpop)
        }
        dataPhoto.recycle() // Pastikan untuk recycle array yang diambil
        return listKpop
    }


    private fun showRecyclerList() {
        rvKpopSuperstars.layoutManager = LinearLayoutManager(this)
        val listKpopAdapter = KpopSuperstarsAdapter(list)
        rvKpopSuperstars.adapter = listKpopAdapter

        listKpopAdapter.setOnItemClickCallback(object : KpopSuperstarsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: KpopSuperstars) {
                val moveIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveIntent.putExtra(DetailActivity.EXTRA_KPOP, data)
                startActivity(moveIntent)
            }
        })
    }
}
