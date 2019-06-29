import kotlinx.coroutines.*


fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch (Dispatchers.Default) {
       try {
           println("job: I'M sleeping 1 ...")
           delay(1000L)
       } finally {
           // finallyの途中でキャンセルされても実行される
           withContext(NonCancellable) {
               delay(1000L)
               println("job: And I've just delayed for 1 sec because I'm non-cancellable")
           }
       }
    }

    delay(1300L)
    println("main: I'm tiered of waiting!")
    job.cancelAndJoin()
    println("main: Now I can quit.")
}