package chapter_8

import kotlinx.coroutines.*

/*
Иметь всего одну корутину - идеальный случай. Но что если у нас будет несколько
корутин с другими дочерними корутинами, которые могу выбросить исключение?
В этом случае победит первое выброшенное исключение. Если мы добавим
CoroutineExceptionHandler, то он перехватит только первое исключение.
 */
fun main() = runBlocking {
    val coroutineExceptionHandler =
        CoroutineExceptionHandler { _, error ->
            println("Caught $error with suppressed ${error.suppressed?.contentToString()}")
        }

    val parentJob = GlobalScope.launch(coroutineExceptionHandler) {
        launch {
            try {
                delay(Long.MAX_VALUE)
            } catch (e: Exception) {
                println("${e.javaClass.simpleName} in Child Job 1")
            } finally {
                throw ArithmeticException()
            }
        }

        launch {
            delay(100)
            throw IllegalStateException()
        }

        delay(Long.MAX_VALUE)
    }

    parentJob.join()
}