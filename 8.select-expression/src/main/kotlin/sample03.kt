package sample03

import com.sun.management.jmx.Trace.send
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.selects.select
import java.util.*

fun CoroutineScope.produceNumbers(side: SendChannel<Int>) = produce<Int> {
    for (num in 1..10) {
        delay(100)
        select<Unit> {
            onSend(num) {}
            side.onSend(num) {}
        }
    }
}

fun main(args: Array<String>) = runBlocking{
    val side = Channel<Int>()
    launch {
        side.consumeEach { println("${System.currentTimeMillis()} Side channel has $it") }
    }

    produceNumbers(side).consumeEach {
        println("${System.currentTimeMillis()} Consuming $it")
        delay(250)
    }

    println("Done consuming")
    coroutineContext.cancelChildren()
}