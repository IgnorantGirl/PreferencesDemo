package com.hui.preferencesdemo

import android.annotation.SuppressLint
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.contentValuesOf
import com.hui.preferencesdemo.databinding.ActivitySqliteBinding
import tools.MyDatabaseHelper
import tools.cvOf

class SQLiteActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySqliteBinding

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_sqlite)
        binding = ActivitySqliteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 实例化helper对象
        val dbHelper = MyDatabaseHelper(this, "Book.db", 2)
        binding.createDatabase.setOnClickListener {
            // 创建或打开一个现有的数据库
            dbHelper.writableDatabase
        }

        binding.addData.setOnClickListener {
            // 添加数据
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, values1) // 插入第一条数据
            val values2 = ContentValues().apply {
                // 开始组装第二条数据
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }

            db.insert("Book", null, values2) // 插入第二条数据

            // 使用封装好的方法  插入数据
            val values3 = cvOf("name" to "The Da ","author" to
                    "George ","pages" to 328, "price" to 10.90)
            db.insert("Book", null, values3) // 插入第三条数据

            // 使用ktx库中提供的contentValuesOf方法
            val values4 = contentValuesOf("name" to "Game of Thrones", "author" to "George Martin",
                "pages" to 720, "price" to 20.85)
            db.insert("Book", null, values4)
        }

        binding.updateData.setOnClickListener {
            // 更新数据
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
        }

        binding.deleteData.setOnClickListener {
            // 删除数据
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))
        }

        binding.queryData.setOnClickListener {
            // 查询数据
            val db = dbHelper.readableDatabase
            val cursor = db.rawQuery("select * from Book", null)
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d("SQLiteActivity", "book: $name,$author,$pages,$price")
                } while (cursor.moveToNext())
            }
        }

        binding.replaceData.setOnClickListener {
            // 替换数据
            val db = dbHelper.writableDatabase
            db.beginTransaction() // 开启事务
            try {
                db.delete("Book", null, null)
                if (true) {
                    // 手动抛出一个异常，让事务失败 观察是否删除原数据成功
                    throw NullPointerException()
                }
                val values = ContentValues().apply {
                    put("name", "Game of Thrones")
                    put("author", "George Martin")
                    put("pages", 720)
                    put("price", 20.85)
                }
                db.insert("Book", null, values)
                db.setTransactionSuccessful() // 事务已经执行成功
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction() // 结束事务
            }
        }
    }
}