import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main(args: Array<String>) = runBlocking {
    data class Ball(var hits : Int)

    suspend fun player(name: String, table: Channel<Ball>) {
        for (ball in table) {
            ball.hits++
            println("$name $ball")
            delay(300)
            table.send(ball)
        }
    }

    val table = Channel<Ball>()
    launch { player("ping", table) }
    launch { player("pong", table) }
    table.send(Ball(0))
    delay(1000)
    coroutineContext.cancelChildren()
}