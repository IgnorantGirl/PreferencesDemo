package tools

import android.content.ContentValues

/**
 * 构建ContentValues对象
 * @param pairs 键值对 接收一个Pair参数，也就是使用A to B语法结构创建出来的参数类型
 * @return ContentValues对象
 */
fun cvOf(vararg pairs: Pair<String, Any?>): ContentValues {
    val cv = ContentValues()
    for (pair in pairs) {
        val key = pair.first
        when (val value = pair.second) {
            // when语句进入Int条件分支后，这个条件下面的value会被自动转换成Int类型，而不再是Any?类型
            // 这样我们就不需要像Java中那样再额外进行一次向下转型
            is Int -> cv.put(key, value)
            is Long -> cv.put(key, value)
            is Short -> cv.put(key, value)
            is Boolean -> cv.put(key, value)
            is Float -> cv.put(key, value)
            is Double -> cv.put(key, value)
            is String -> cv.put(key, value)
            is Byte -> cv.put(key, value)
            is ByteArray -> cv.put(key, value)
            null -> cv.putNull(key)
        }
    }
    return cv;
}


// 使用高阶函数
fun cvOf1(vararg pairs: Pair<String, Any?>) = ContentValues().apply {
    for (pair in pairs) {
        val key = pair.first
        when (val value = pair.second) {
            // apply函数的Lambda表达式中会自动拥有ContentValues的上下文
            // 所以这里可以直接调用ContentValues的各种put方法
            is Int -> put(key, value)
            is Long -> put(key, value)
            is Short -> put(key, value)
            is Boolean -> put(key, value)
            is Float -> put(key, value)
            is Double -> put(key, value)
            is String -> put(key, value)
            is Byte -> put(key, value)
            is ByteArray -> put(key, value)
            null -> putNull(key)
        }
    }
}
