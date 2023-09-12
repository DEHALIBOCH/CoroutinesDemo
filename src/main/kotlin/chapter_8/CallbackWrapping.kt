package chapter_8

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

fun main() = runBlocking {
    try {
        val data = getDataAsync()
        println("Data received $data")
    } catch (e: Exception) {
        println("Caught ${e.javaClass.simpleName}")
    }
}

suspend fun getDataAsync(): String {
    return suspendCancellableCoroutine { continuation ->
        getData(object : AsyncCallback {
            override fun onSuccess(result: String) {
                continuation.resume(result)
            }

            override fun onError(e: Exception) {
                continuation.resumeWithException(e)
            }
        })
    }
}

fun getData(asyncCallback: AsyncCallback) {
    val triggerError = false

    try {
        Thread.sleep(3000)
        if (triggerError) {
            throw IOException()
        } else {
            asyncCallback.onSuccess("[Beep.Boop.Beep]")
        }
    } catch (e: Exception) {
        asyncCallback.onError(e)
    }
}

interface AsyncCallback {

    fun onSuccess(result: String)

    fun onError(e: Exception)
}