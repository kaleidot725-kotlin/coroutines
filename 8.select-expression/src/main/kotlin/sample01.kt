package sample01

import com.sun.management.jmx.Trace.send
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun CoroutineScope.fizz() = produce<String> {
    while (true) {
        delay(300)
        send("Fizz")
    }
}

fun CoroutineScope.buzz() = produce<String> {
    while(true) {
        delay(500)
        send("Buzz!")
    }
}

suspend fun selectFizzBuzz(fizz : ReceiveChannel<String>, buzz : ReceiveChannel<String>) {
    select<Unit> {
        fizz.onReceive {
            value -> println("fizz -> '$value")
        }
        buzz.onReceive {
                value -> println("buzz -> '$value")
        }
    }
}

fun main(args: Array<String>) = runBlocking{
    val fizz = fizz()
    val buzz = buzz()

    repeat(7) {
        selectFizzBuzz(fizz, buzz)
    }

    coroutineContext.cancelChildren()
}