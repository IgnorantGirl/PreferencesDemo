package tools

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {

    // 创建数据表 - 图书表
    private val createBook = "create table Book (" +
            " id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)"
    // 创建数据表 - 分类表
    private val createCategory = "create table Category (\n" +
            "    id integer primary key autoincrement,\n" +
            "    category_name text,\n" +
            "    category_code integer)"

    override fun onCreate(db: SQLiteDatabase) {
        // 执行建表语句
        db.execSQL(createBook)
        db.execSQL(createCategory)
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // 直接删表 有些粗暴
        /**
        db.execSQL("drop table if exists Book")
        db.execSQL("drop table if exists Category")
        onCreate(db)
        */
        // 可以采用版本号判断，进行升级数据库

        // 注意：每当升级一个数据库版本的时候，onUpgrade()方法里都一定要写一个相应的if判断语句。
        // 这是为了保证App在跨版本升级的时候，每一次的数据库修改都能被全部执行
        if (oldVersion <= 1 ) {
            db.execSQL(createCategory)
        }
        if (oldVersion <= 2) {
            db.execSQL("alter table Book add column category_id integer")
        }
    }

}