import kotlinx.coroutines.*


fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch (Dispatchers.Default) {
       try {
//           repeat(1000) { i ->
//               println("job: I'M sleeping $i ...")
//               delay(500L)
//           }
           delay(1000L)
       } finally {
           delay(1000L)
           println("job: I'm running finally")
       }
    }

    delay(1300L)
    println("main: I'm tiered of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit.")
}