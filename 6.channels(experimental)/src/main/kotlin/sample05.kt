import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce


fun main(args: Array<String>) = runBlocking {
    fun CoroutineScope.numbersForm(start : Int) = produce<Int> {
        var x = start
        while (true) send(x++)
    }

    fun CoroutineScope.filter(numbers : ReceiveChannel<Int>, prime : Int) : ReceiveChannel<Int> = produce {
        for (x in numbers) if (x % prime != 0) send(x)
    }

    var cur = numbersForm(2)
    for (i in 1..10) {
        val prime = cur.receive()
        println(prime)
        cur = filter(cur, prime)
    }
    coroutineContext.cancelChildren()
}