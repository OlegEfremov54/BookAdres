package com.example.bookadres

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    private lateinit var toolbarSec: Toolbar

    private lateinit var nameTV: TextView
    private lateinit var familyTV: TextView
    private lateinit var adresTV: TextView
    private lateinit var phoneTV: TextView

    private lateinit var buttonBackBTN: Button

    var user: Person? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarSec = findViewById(R.id.toolbarSec)
        setSupportActionBar(toolbarSec)
        title = " Адресная книга"
        toolbarSec.subtitle = "Подробная информация профиля"
        toolbarSec.setLogo(R.drawable.adresbook2)

        nameTV = findViewById(R.id.nameTV)
        familyTV = findViewById(R.id.familyTV)
        adresTV = findViewById(R.id.adresTV)
        phoneTV = findViewById(R.id.phoneTV)
        buttonBackBTN = findViewById(R.id.buttonBackBTN)

        user = intent.extras?.getParcelable(Person::class.java.simpleName) as Person?
        val name = user?.name.toString()
        val family = user?.family.toString()
        val adres = user?.adres.toString()
        val phone = user?.phone.toString()

        nameTV.text = name
        familyTV.text = family
        adresTV.text = adres
        phoneTV.text = phone

        buttonBackBTN.setOnClickListener { view ->
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.infoMenuMain -> {
                Toast.makeText(
                    applicationContext, "Автор Ефремов О.В. Создан 9.11.2024",
                    Toast.LENGTH_LONG
                ).show()
            }

            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext, "Работа приложения завершена",
                    Toast.LENGTH_LONG
                ).show()
                finishAffinity()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}



