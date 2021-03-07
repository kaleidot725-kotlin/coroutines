import kotlinx.coroutines.*

fun main() {
//    launchOnDefaultJobScope()
//    launchOnSupervisorJobScope()
    launchAsyncOnDefaultJobScope()
    launchAsyncOnSupervisorScope()
//    launchSafeAsync()
}

/**
 * 通常の Job を持った CoroutineScope で起動した場合
 */
private fun launchOnDefaultJobScope() = runBlocking {
    // Job を指定していない場合は CoroutineScope 初期化時に Job を設定してくれるようになっている。
    // なので特に Job を指定しない場合には通常の Job で動作するようになる。
    val scope = CoroutineScope(Dispatchers.Default)

    scope.launch {
        throw Exception("GOOD ERROR MESSAGE!!")
    }

    scope.launch {
        println("GOOD PRINT")
    }

    delay(1000)
}

/**
 * 通常の Job を持った CoroutineScope で async を使って起動した場合
 */
private fun launchAsyncOnDefaultJobScope() = runBlocking {
    // Job を指定していない場合は CoroutineScope 初期化時に JOB を設定してくれるようになっている。
    // 通常の JOB ではある子が失敗したら他の子に失敗が連鎖し動作がとまってしまうらしい
    val scope = CoroutineScope(Dispatchers.Default)

    // async を使って await したときに例外した場合には以下のように try catch で wrapping すると例外が拾える
    // これで問題ないように見えるが async は1つの Job になるので、ここで発生した例外はすべての 子 JOB に影響してしまう。
    scope.launch {
        try {
            async {
                delay(100)
                throw Exception("DEFFERED ERROR MESSAGE!!")
            }.await()
        } catch (e: Exception) {
            println(e.message)
        }
    }

    scope.launch {
        delay(200)
        println("GOOD PRINT")
    }

    delay(1000)
}

/**
 * SupervisorJob を持った CoroutineScope で起動した場合
 */
private fun launchOnSupervisorJobScope() = runBlocking {
    // SupervisorJob を設定した場合は子の Job のエラーが他の Job に伝搬しないようになる
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    scope.launch {
        throw Exception("GOOD ERROR MESSAGE!!")
    }

    scope.launch {
        println("GOOD PRINT")
    }

    delay(1000)
}

/**
 * SupervisorJob を持った CoroutinesScope で aysnc を使って起動した場合
 */
private fun launchAsyncOnSupervisorScope() = runBlocking {
    // SupervisorJob を設定した場合は子の Job のエラーが他の Job に伝搬しないようになる
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    // async を使って await したときに例外した場合には以下のように try catch で wrapping すると例外が拾える
    // SupervisorJob なので async ブロック内で発生したエラーは他の Job に伝搬しない。
    scope.launch {
        try {
            async {
                delay(100)
                throw Exception("DEFFERED ERROR MESSAGE!!")
            }.await()
        } catch (e: Exception) {
            println(e.message)
        }
    }

    scope.launch {
        delay(200)
        println("GOOD PRINT")
    }

    delay(1000)
}

/**
 * async で発生した例外を他の Job に伝搬させないために async 内で例外を catch する
 */
private fun launchSafeAsync() = runBlocking {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    scope.launch {
        async {
            try {
                delay(100)
                throw Exception("DEFFERED ERROR MESSAGE!!")
            } catch (e: Exception) {
                println(e.message)
            }
        }.await()
    }

    scope.launch {
        delay(200)
        println("GOOD PRINT")
    }

    delay(1000)
}