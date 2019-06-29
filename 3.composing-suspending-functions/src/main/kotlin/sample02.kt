import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking{
    suspend  fun doSomethingUsefulOne() : Int {
        delay(1000L)
        println("one")
        return 13
    }

    suspend  fun doSomethingusefulTwo() : Int {
        delay(1000L)
        println("two")
        return 29
    }

    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingusefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }

    println("Completed in $time ms")
}

