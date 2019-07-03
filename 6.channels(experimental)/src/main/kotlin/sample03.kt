import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce


fun main(args: Array<String>) = runBlocking {
    fun CoroutineScope.produceSquares() : ReceiveChannel<Int> = produce {
        for (x in 1..5) send(x * x)
    }

    val squares = produceSquares()
    squares.consumeEach { println(it) }
    println("Done!")
}