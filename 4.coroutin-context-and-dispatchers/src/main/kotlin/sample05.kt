import kotlinx.coroutines.*

fun main() = runBlocking<Unit>{
    val request = launch {
        GlobalScope.launch {
            println("job1: I run in GlobalScope and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation of the request")
        }

        launch {
            delay(100)
            println("job2: I am a child of the request coroutin")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
    }

    delay(500)
    request.cancel()
    delay(1000)
    println("main: Who has survived request cancellation?")
}