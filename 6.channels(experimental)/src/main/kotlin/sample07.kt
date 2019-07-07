import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main(args: Array<String>) = runBlocking {
    suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
        while(true) {
            delay(time)
            channel.send(s)
        }
    }

    val channel = Channel<String>()
    launch {sendString(channel, "foo", 200L)}
    launch {sendString(channel, "BAR!", 500L)}
    repeat(6) {
        println(channel.receive())
    }

    coroutineContext.cancelChildren()
}