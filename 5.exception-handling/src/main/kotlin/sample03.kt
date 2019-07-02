import kotlinx.coroutines.*
import java.lang.ArithmeticException
import java.lang.AssertionError
import java.lang.IndexOutOfBoundsException

fun main() = runBlocking {
    val job = launch {
        val child = launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("Child is canceled")
            }
        }

        yield()
        println("Cancelling child")
        child.cancel()
        child.join()
        yield()
        println("Parent is not cancelled")
    }

    job.join()
}