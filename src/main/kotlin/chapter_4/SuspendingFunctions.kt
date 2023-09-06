package chapter_4

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

fun getUserStandard(
    userId: String,
    onUserResponse: (User?, Throwable?) -> Unit,
) {
    thread {
        try {
            Thread.sleep(1000)
            val user = User(userId, "Filip")
            onUserResponse(user, null)
        } catch (error: Throwable) {
            onUserResponse(null, error)
        }
    }
}

suspend fun getUserSuspend(userId: String) : User {
    delay(1000)

    return User(userId, "Filip")
}

fun main() {
    GlobalScope.launch {
        val user = getUserSuspend("101")

        println(user)
    }
    Thread.sleep(1500)
}