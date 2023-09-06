package chapter_4

import kotlin.concurrent.thread

fun getUserStandard(
    userId: String,
    onUserReady: (User) -> Unit,
) {
    thread {
        Thread.sleep(1000)

        val user = User(userId, "Filip")
        onUserReady(user)
    }
    println("end")
}


data class User(val userId: String, val name: String)