import kotlinx.coroutines.*
import java.lang.ArithmeticException
import java.lang.AssertionError
import java.lang.IndexOutOfBoundsException

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Cauthg $exception")
    }

    val job = GlobalScope.launch(handler) {
        throw AssertionError()
    }

    val deferred = GlobalScope.async(handler) {
        throw ArithmeticException()
    }

    joinAll(job, deferred)
}