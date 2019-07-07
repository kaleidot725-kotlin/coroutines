package sample04

import javafx.application.Application.launch
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis


fun main(args: Array<String>) = runBlocking {
    suspend fun massiveRun(action: suspend  () -> Unit) {
        val n = 100
        val k = 1000
        val time = measureTimeMillis {
            coroutineScope {
                repeat(n) {
                    launch {
                        repeat(k) { action() }
                    }
                }
            }
        }
        println("Completed ${n * k} actions in $time ms")
    }

    val counterCotext = newSingleThreadContext("CounterContext")
    var counter = 0

    withContext(Dispatchers.Default) {
        massiveRun {
            withContext(counterCotext) {
                counter++
            }
        }
    }

    println("Counter = $counter")
}