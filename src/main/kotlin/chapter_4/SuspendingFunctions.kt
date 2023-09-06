package chapter_4

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

fun main() {
    getUserStandard("101") { user, throwable ->
        user?.run(::println)

        throwable?.printStackTrace()
    }
}