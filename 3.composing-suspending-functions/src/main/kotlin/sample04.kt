import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
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

    fun somethingUsefulOneAsync() = GlobalScope.async {
        doSomethingUsefulOne()
    }

    fun somethingUsefulTwoAsync() = GlobalScope.async {
        doSomethingusefulTwo()
    }

    // soethingUsefulOneAsyncとone.awaitの間で間違いがあると難しくなるのでおすすめしない
    val time = measureTimeMillis {
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }

    println("Completed in $time ms")
}

