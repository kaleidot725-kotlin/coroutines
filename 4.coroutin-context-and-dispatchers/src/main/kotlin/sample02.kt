import kotlinx.coroutines.*

fun main() = runBlocking<Unit>{
    launch(Dispatchers.Unconfined) {
        println("Unconfined : I'm working in thread ${Thread.currentThread()}")
        delay(500) // 呼びだされた中断関数によって決定されたスレッドで再開される
        println("Unconfined : After delay in thread ${Thread.currentThread()}")
    }

    launch {
        println("main runBlocking : I'm working in thread ${Thread.currentThread()}")
        delay(1000)
        println("main runBlocking : After delay in thread ${Thread.currentThread()}")
    }
}