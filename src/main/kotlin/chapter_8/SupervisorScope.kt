package chapter_8

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() = runBlocking {
    supervisorScope {

        val result = async {
            println("Throwing exception in async")
            throw IllegalStateException()
        }

        try {
            result.await()
        } catch (e: Exception) {
            println("Caught $e")
        }
    }
}