import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    launch { doWorld() }
    println("Hello,")
}

suspend fun doWorld() {
    delay(1000L)
    println("World!")
}