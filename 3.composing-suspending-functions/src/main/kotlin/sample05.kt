import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    suspend fun doSomethingUsefulOne() : Int {
        delay(1000L)
        println("one")
        return 13
    }

    suspend fun doSomethingusefulTwo() : Int {
        delay(1000L)
        println("two")
        return 29
    }

    suspend fun concurrentSum() : Int = coroutineScope {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingusefulTwo() }
        one.await() + two.await()
    }

    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")
}

