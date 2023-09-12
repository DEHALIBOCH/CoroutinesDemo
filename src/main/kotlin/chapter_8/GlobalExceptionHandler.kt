package chapter_8

import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val exceptionHandler =
            CoroutineExceptionHandler { _, throwable ->
                println("Caught $throwable")
            }
        val job = GlobalScope.launch(exceptionHandler) {
            throw AssertionError("Sample Assertion Error!")
        }
        val deferred = GlobalScope.async(exceptionHandler) {
            // Не будет выбрашено т.к. для выброса исключения из deffered(async) нужно
            // вызвать на deffered функцию await()
            throw ArithmeticException()
        }
        joinAll(job, deferred)
    }
}