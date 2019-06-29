import kotlinx.coroutines.*


fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch (Dispatchers.Default) {
       try {
           println("job: I'M sleeping 1 ...")
           delay(1000L)
       } finally {
           // finallyの処理中にCancelされると実行されない
           delay(1000L)
           println("job: I'm running finally")
       }
    }

    delay(1300L)
    println("main: I'm tiered of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit.")
}