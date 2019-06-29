import kotlinx.coroutines.*

fun main() = runBlocking<Unit>{
    launch {
        println("main runBlocking : I'm working in thread ${Thread.currentThread()}")
    }

    launch(Dispatchers.Unconfined) {
        println("Unconfined : I'm working in thread ${Thread.currentThread()}")
    }

    launch(Dispatchers.Default) {
        println("Default : I'm working in thread ${Thread.currentThread()}")
    }

    launch(newSingleThreadContext("MyOwnThread")) {
        println("newSingleThreadContext : I'm working in thread ${Thread.currentThread()}")
    }
}