package com.hui.preferencesdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hui.preferencesdemo.databinding.ActivityFileBinding
import com.hui.preferencesdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnFile.setOnClickListener{
            val intent = Intent(this, FileActivity::class.java)
            startActivity(intent)
        }

        binding.btnSharedPreferences.setOnClickListener{
            val intent = Intent(this, SharedPreferencesActivity::class.java)
            startActivity(intent)
        }

        binding.btnSqlite.setOnClickListener{
            val intent = Intent(this, SQLiteActivity::class.java)
            startActivity(intent)
        }
        binding.test.setOnClickListener{
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        }
    }
}