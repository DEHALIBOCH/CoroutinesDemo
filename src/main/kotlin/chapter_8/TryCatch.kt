package chapter_8

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val callAwaitOnDeferred = false

        val deferred = GlobalScope.async {
            // println выведет в консоль эту строку без вызова deffered.await()
            println("Throwing exception from async")
            throw ArithmeticException() // Исключение проброшено в переменную
            // и будет проброшено в функции main только если мы вызовем deffered.await()
        }

        if (callAwaitOnDeferred) {
            try {
                deferred.await()
            } catch (e: ArithmeticException) {
                println("Caught Arithmetic Exception")
            }
        }
    }
}