import jdk.nashorn.internal.objects.Global
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.ArithmeticException
import java.lang.AssertionError
import java.lang.IndexOutOfBoundsException

fun main() = runBlocking {
    try {
        supervisorScope {
            val child = launch {
                try {
                    println("Child is sleeping")
                    delay(Long.MAX_VALUE)
                } finally {
                    println("Child is cancelled")
                }
            }


            yield()
            println("Throwing exception from scope")
            throw AssertionError()
        }
    }catch (e: AssertionError) {
        println("Caught assertion error")
    }
}