package chapter_6

import chapter_6.coroutine_context_ptovider.CoroutineContextProvider
import chapter_6.coroutine_context_ptovider.CoroutineContextProviderImpl
import kotlinx.coroutines.*

//fun main() {
//    GlobalScope.launch {
//        println("In a coroutine")
//    }
//    Thread.sleep(50)
//}

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    val parentJob = Job()
    val provider: CoroutineContextProvider = CoroutineContextProviderImpl(
        context = parentJob + Dispatchers.IO
    )
    GlobalScope.launch(context = provider.context()) {
        println(Thread.currentThread().name)
    }
    Thread.sleep(50)
}