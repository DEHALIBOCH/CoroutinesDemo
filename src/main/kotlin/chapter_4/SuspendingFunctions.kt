package chapter_4

import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private fun getUserStandard(
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

private suspend fun getUserSuspend(userId: String): User = withContext(Dispatchers.Default) {
    delay(1000)

    User(userId, "Filip")
}

fun main() {
    GlobalScope.launch {
        val user = getUserSuspend("101")

        println(user)
    }
    Thread.sleep(1500)
}


// Some function from other library, that we can't change
private fun readFile(path: String, onReady: (File) -> Unit) {
    // Some Heavy operation
    Thread.sleep(1000)
    // Callback
    onReady(File(path))
}

// We can easily convert this fun to suspend fun
private suspend fun readFileSuspend(path: String): File = suspendCoroutine {
    readFile(path) { file ->
        it.resume(file)
    }
}