import jdk.nashorn.internal.objects.Global
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.ArithmeticException
import java.lang.AssertionError
import java.lang.IndexOutOfBoundsException

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught original $exception")
    }

    val job = GlobalScope.launch(handler) {
        val inner = launch {
            launch {
                launch {
                    throw IOException()
                }
            }
        }

        try {
            inner.join()
        } catch (e: CancellationException) {
            println("Rethrowing CancellationException with original cause")
            throw e
        }
    }

    job.join()
}