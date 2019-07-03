import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce


fun main(args: Array<String>) = runBlocking {
    fun CoroutineScope.produceNumbers() = produce<Int> {
        var x = 1
        while (true) send(x++)
    }

    fun CoroutineScope.square(numbers : ReceiveChannel<Int>) : ReceiveChannel<Int> = produce {
        for (x in numbers) send(x * x)
    }

    val numbers = produceNumbers()
    val squares = square(numbers)
    for (i in 1..100) println(squares.receive())
    println("Done!")
    coroutineContext.cancelChildren()
}