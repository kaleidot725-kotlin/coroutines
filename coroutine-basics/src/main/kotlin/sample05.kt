import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    launch {
        delay(200L)
        println("Task from  runBlocking")
    }

    coroutineScope {
        launch {
            delay(200L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope")
    }

    println("Coroutine scope is over")
}