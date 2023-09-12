package chapter_8

import kotlinx.coroutines.*
import java.lang.IndexOutOfBoundsException

@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {
    val launchJob = GlobalScope.launch {
        println("1. Exception created via launch coroutine")
        throw IndexOutOfBoundsException()
    }
    launchJob.join()
    println("2. Joined failed job")

    val deferred = GlobalScope.async {
        println("3. Exception created via async coroutine")
        throw ArithmeticException()
    }

    try {
        deferred.await()
        println("4. Unreachable, this statement is never executed")
    } catch (e: Exception) {
        println("5. Caught ${e.javaClass.simpleName}")
    }
}