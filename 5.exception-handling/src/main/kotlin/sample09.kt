import jdk.nashorn.internal.objects.Global
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.ArithmeticException
import java.lang.AssertionError
import java.lang.IndexOutOfBoundsException

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }

    supervisorScope {
        val child = launch(handler) {
            println("Child throws an exception")
            throw AssertionError()
        }
        println("Scope is completing")
    }
    println("Scope is completed")
}