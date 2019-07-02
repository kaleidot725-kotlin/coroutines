import kotlinx.coroutines.*
import java.lang.ArithmeticException
import java.lang.IndexOutOfBoundsException

fun main() = runBlocking {
    val job = GlobalScope.launch {
        println("Throwing exception from launch")
        throw IndexOutOfBoundsException()
    }
    job.join()

    println("Joined failed job")
    val deferred = GlobalScope.async {
        println("Throwing exception from async")
        throw ArithmeticException()
    }

    try {
        deferred.await()
        println("Unreached")
    } catch (e : ArithmeticException) {
        println("Caught ArithmeticException")
    }
}