package sample02

import com.sun.management.jmx.Trace.send
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

suspend fun selectFizzBuzz(a : ReceiveChannel<String>, b : ReceiveChannel<String>) {
    select<Unit> {
        a.onReceiveOrNull { value ->
            if (value == null)
                println("Channel 'a' is closed")
            else
                println("a -> '$value")
        }
        b.onReceive { value ->
            if (value == null)
                println("Channel 'b' is closed")
            else
                println("b -> '$value")
        }
    }
}

fun main(args: Array<String>) = runBlocking{
    val a = produce<String> { repeat(4) {send("Hello $it")} }
    val b = produce<String> { repeat(4) {send("World $it")} }

    repeat(8) {
        selectFizzBuzz(a, b)
    }

    coroutineContext.cancelChildren()
}