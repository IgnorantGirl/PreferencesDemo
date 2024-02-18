package tools

import android.content.SharedPreferences

/**
 * 通过扩展函数的方式向SharedPreferences类中添加了一个open函数
 * 函数的参数 block: SharedPreferences.Editor.() -> Unit 是一个函数类型，具有特定的接收者和返回类型
 * block: 这是参数的名字，代表传入的一个函数（或者说是一个代码块）。
 * SharedPreferences.Editor.() -> Unit: 这是参数的类型，
 *      表示这个函数类型具有 SharedPreferences.Editor 类型的接收者，并且没有返回值（返回类型为 Unit）。
 */
fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
    // 调用 SharedPreferences.edit() 方法获取一个 SharedPreferences.Editor 对象
    val editor = edit()
    // 调用传入的 block 函数（代码块），并在 editor 对象的上下文中执行它
    // 由于 block 具有 SharedPreferences.Editor 类型的接收者
    // 故你可以在 block 内部直接调用 SharedPreferences.Editor 的方法。
    editor.block()
    editor.apply()
}
// 注意
// 当你在 block 内部编写代码时，你可以直接调用 SharedPreferences.Editor 的方法，而不需要通过一个对象来调用。
// 这是因为 block 有一个隐式的接收者，它的类型是 SharedPreferences.Editor。

/**
 Google提供的KTX扩展库中已经包含了上述SharedPreferences的简化用法
 implementation("androidx.core:core-ktx:1.9.0")
 使用方法：
 *  getSharedPreferences("data", MODE_PRIVATE).edit {
 *                 putString("name", "Tom")
 *                 putInt("age", 28)
 *                 putBoolean("married", false)
 *             }
 */