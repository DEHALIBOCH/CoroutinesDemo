package chapter_4

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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

suspend fun getUserSuspend(userId: String): User {
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


// Some function from other library, that we can't change
fun readFile(path: String, onReady: (File) -> Unit) {
    // Some Heavy operation
    Thread.sleep(1000)
    // Callback
    onReady(File(path))
}

// We can easily convert this fun to suspend fun
suspend fun readFileSuspend(path: String): File = suspendCoroutine {
    readFile(path) { file ->
        it.resume(file)
    }
}