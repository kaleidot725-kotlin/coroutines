import kotlinx.coroutines.*

fun main() = runBlocking<Unit>{
    fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

    val a = async {
        log("I'm computing a piece of the answer")
        6
    }

    val b = async {
        log("I'm computing another piece of the answer")
        7
    }

    log("The anwer is ${a.await() * b.await()}")
}