package com.example.bookadres

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var toolbarMain: Toolbar

    private lateinit var nameET:EditText
    private lateinit var familyET:EditText
    private lateinit var adresET:EditText
    private lateinit var phoneET:EditText

    private lateinit var listUsersLV: ListView

    private lateinit var saveBTN:Button

    val users: MutableList<Person> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = " Адресная книга"
        toolbarMain.subtitle = "  Версия 1. Стартовая страница"
        toolbarMain.setLogo(R.drawable.adresbook)

        nameET = findViewById(R.id.nameET)
        familyET = findViewById(R.id.familyET)
        adresET = findViewById(R.id.adresET)
        phoneET = findViewById(R.id.phoneET)
        saveBTN = findViewById(R.id.saveBTN)

        listUsersLV = findViewById(R.id.listUsersLV)


        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
        listUsersLV.adapter = adapter

        saveBTN.setOnClickListener {
            if (nameET.text.isEmpty() ||
                familyET.text.isEmpty() ||
                adresET.text.isEmpty() ||
                phoneET.text.isEmpty()
            ) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            users.add(Person(nameET.text.toString(),
                familyET.text.toString(),
                adresET.text.toString(),
                phoneET.text.toString()))

            adapter.notifyDataSetChanged()
                nameET.text.clear()
                familyET.text.clear()
                adresET.text.clear()
                phoneET.text.clear()

        }

        listUsersLV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val user = adapter.getItem(position)
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra(Person::class.java.simpleName, user)
                startActivity(intent)

            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.infoMenuMain -> {
                Toast.makeText(applicationContext, "Автор Ефремов О.В. Создан 9.11.2024",
                    Toast.LENGTH_LONG).show()
            }
            R.id.exitMenuMain ->{
                Toast.makeText(applicationContext, "Работа приложения завершена",
                    Toast.LENGTH_LONG).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}