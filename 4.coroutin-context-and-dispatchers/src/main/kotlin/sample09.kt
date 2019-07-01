import kotlinx.coroutines.*

fun main() = runBlocking<Unit>{
    class Activity : CoroutineScope by CoroutineScope(Dispatchers.Default){
        fun doSomething() {
            repeat(10) { i ->
                launch {
                    delay((i + 1) * 200L)
                    println("Coroutine $i is done")
                }
            }
        }

        fun destroy() {
            cancel()
        }
    }

    val activity = Activity()
    activity.doSomething()
    println("Launched coroutines")
    delay(500L)
    println("Destroying acitivty!")
    activity.destroy()
    delay(1000)
}