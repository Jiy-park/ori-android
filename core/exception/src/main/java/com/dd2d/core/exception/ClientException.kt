package com.dd2d.core.exception

sealed class ClientException(
    message: String,
    code: Int? = null,
    cause: Throwable?
): ManagedException(message = "$message($code)", cause = cause) {
    class OperationFailException(
        message: String,
        code: Int? = null,
        cause: Throwable? = null
    ): ClientException(message = message, code = code, cause = cause)
}