fun ignoreException(block: () -> Unit) {
    try {
        block()
    } catch (_: Exception) {
    }
}

fun <R> ignoreExceptionOrNull(block: () -> R): R? =
    try {
        block()
    } catch (_: Exception) {
        null
    }
