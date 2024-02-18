package com.hui.preferencesdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.edit
import com.hui.preferencesdemo.databinding.ActivitySharedPreferencesBinding

class SharedPreferencesActivity : AppCompatActivity() {
    private lateinit var  binding: ActivitySharedPreferencesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveButton.setOnClickListener {
            // 向SharedPreferences中保存数据
            val editor = getSharedPreferences("data", MODE_PRIVATE).edit()
            editor.putString("name", "Tom")
            editor.putInt("age", 18)
            editor.putBoolean("married", true)
            editor.apply()


            // 实际上可以直接在项目中使用如下写法来向SharedPreferences存储数据
//             getSharedPreferences("data", MODE_PRIVATE).edit {
//                putString("name", "Tom")
//                putInt("age", 28)
//                putBoolean("married", false)
//            }
        }
        binding.restoreButton.setOnClickListener {
            // 从SharedPreferences中读取数据
            val prefs = getSharedPreferences("data", MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)
            Toast.makeText(this,"name: $name, age: $age, married: $married", Toast.LENGTH_SHORT).show()
        }
    }
}