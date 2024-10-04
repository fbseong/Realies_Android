package com.selfpro.realies.util.common

import android.util.Log

class SpLog {
    companion object {
        val isDebugMode: Boolean = true
        val preTag: String = "Sp_"

        fun e(tag: String, msg: String) {
            if (isDebugMode) {
                val stackTrace = Exception().stackTrace[1]
                val lineNumber = "(${stackTrace.fileName}:${stackTrace.lineNumber})"

                Log.e(
                    preTag + tag,
                    "$msg\n\tat ${stackTrace.className}.${stackTrace.methodName}$lineNumber"
                )
            }
        }

        fun e(tag: String, e: Error) {
            if (isDebugMode) {
                val errMsg = getErrorlog(e)
                val stackTrace = Exception().stackTrace[1]
                val lineNumber = "(${stackTrace.fileName}:${stackTrace.lineNumber})"
                Log.e(
                    preTag + tag,
                    "$errMsg\n\tat ${stackTrace.className}.${stackTrace.methodName}$lineNumber"
                )

            }
        }

        fun e(tag: String, e: Exception) {
            if (isDebugMode) {
                val errMsg = getErrorlog(e)
                val stackTrace = Exception().stackTrace[1]
                val lineNumber = "(${stackTrace.fileName}:${stackTrace.lineNumber})"
                Log.e(
                    preTag + tag,
                    "$errMsg\n\tat ${stackTrace.className}.${stackTrace.methodName}$lineNumber"
                )
            }
        }

        fun e(tag: String, e: Throwable) {
            if (isDebugMode) {
                val stackTrace = Exception().stackTrace[1]
                val lineNumber = "(${stackTrace.fileName}:${stackTrace.lineNumber})"

                Log.e(
                    preTag + tag,
                    "$e\n\tat ${stackTrace.className}.${stackTrace.methodName}$lineNumber"
                )
            }
        }


        fun i(tag: String, msg: String) {
            if (isDebugMode) {
                Log.i(preTag + tag, msg)
            }
        }

        fun v(_tag: String, msg: String) {
            if (isDebugMode) {
                Log.v(preTag + _tag, msg)
            }
        }

        fun d(tag: String, msg: String) {
            if (isDebugMode) {
                val stackTrace = Exception().stackTrace[1]
                val lineNumber = "(${stackTrace.fileName}:${stackTrace.lineNumber})"
                Log.d(preTag + tag, msg + lineNumber)
            }
        }

        fun d(msg: Any) {
            if (isDebugMode) {
                val stackTrace = Exception().stackTrace[1]
                val lineNumber = "(${stackTrace.fileName}:${stackTrace.lineNumber})"
                Log.d(preTag + "Log", "$msg" + lineNumber)
            }
        }


        fun getErrorlog(e: Exception): String {
            val _sb = StringBuffer("")
            try {
                _sb.append(e.toString())
                _sb.append("\n")

                var _element: Array<StackTraceElement> = e.stackTrace

                for (_value in _element) {
                    _sb.append("\tat")
                    _sb.append(_value.toString())
                    _sb.append("\n")
                }
            } catch (ex: Exception) {
                return e.toString()
            }
            return _sb.toString()
        }


        fun getErrorlog(e: Error): String {
            val sb = StringBuffer("")
            try {
                sb.append(e.toString())
                sb.append("\n")

                var _element: Array<StackTraceElement> = e.stackTrace

                for (_value in _element) {
                    sb.append("\tat")
                    sb.append(_value.toString())
                    sb.append("\n")
                }
            } catch (ex: Exception) {
                return e.toString()
            }
            return sb.toString()
        }

    }

}