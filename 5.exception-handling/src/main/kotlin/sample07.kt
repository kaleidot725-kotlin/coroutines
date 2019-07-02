import jdk.nashorn.internal.objects.Global
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.ArithmeticException
import java.lang.AssertionError
import java.lang.IndexOutOfBoundsException

fun main() = runBlocking {
    val supervisor = SupervisorJob()
    with(CoroutineScope(coroutineContext + supervisor)) {
        val firstChild = launch(CoroutineExceptionHandler { _, _ -> }) {
            println("First child is failing")
            throw AssertionError("First child is cancelled")
        }

        val secondChild = launch {
            firstChild.join()
            println("First child is cancelled: ${firstChild.isCancelled}, but second one i still active")
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("Second child is cancelled because supervisor is cancelled")
            }
        }

        firstChild.join()
        println("Cancelling supervisor")
        supervisor.cancel()
        secondChild.join()
    }
}