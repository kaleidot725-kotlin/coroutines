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
        val one = doSomethingUsefulOne()
        val two = doSomethingusefulTwo()
        println("The answer is ${one + two}")
    }

    println("Completed in $time ms")
}

