import kotlinx.coroutines.*


fun main() = runBlocking {
    // withTimeoutはNullを返せないのでTimeoutCancellationExceptionが投げられる
    withTimeout(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i...")
            delay(500L)
        }
    }
}