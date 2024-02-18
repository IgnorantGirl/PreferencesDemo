package com.hui.preferencesdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hui.preferencesdemo.databinding.ActivityFileBinding
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class FileActivity : AppCompatActivity() {
    private lateinit var  binding:ActivityFileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_file)
        binding = ActivityFileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // binding.textView.text = "Hello"
        val inputText = load()
        if(inputText.isNotEmpty()){
            binding.editText.setText(inputText)
            binding.editText.setSelection(inputText.length)
            Toast.makeText(this,"restored", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val inputText = binding.editText.text.toString()
        save(inputText)
    }

    /**
     * 保存文件
     */
    private fun save(inputText: String) {
        try {
            // 通过openFileOutput()方法能够得到一个FileOutputStream对象
            // 借助它构建出一个OutputStreamWriter对象
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            // use()会保证在Lambda表达式中的代码全部执行完之后自动将外层的流关闭，这样就不需要再编写一个finally语句，手动去关闭流
            writer.use {
                it.write(inputText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    /**
     * 读取文件
     */
    private fun load(): String {
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return content.toString()
    }
}