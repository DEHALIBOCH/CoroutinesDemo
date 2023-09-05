import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    with(GlobalScope) {
        val parentJob = launch {
            delay(200)
            println("I`m the parent")
            delay(200)
        }
        launch(context = parentJob) {
            delay(200)
            println("I`m a child")
            delay(200)
        }
        if (parentJob.children.iterator().hasNext()) {
            println("The Job has children")
        } else {
            println("The job has NO children")
        }
    }
    Thread.sleep(1000)
}