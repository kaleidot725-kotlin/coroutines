import kotlinx.coroutines.*


fun main() = runBlocking {
    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500)
        }
        "Done"
    }
    println("Result is $result")
}