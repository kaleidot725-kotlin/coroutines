package sample02

import javafx.application.Application.launch
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

@Volatile
var counter = 0

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

    withContext(Dispatchers.Default) {
        massiveRun {
            counter++
        }
    }

    println("Counter = $counter")
}