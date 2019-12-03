package com.bezzo.moviecatalogue.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor


class AppExecutor(val diskIO: Executor,
                  val mainThread: Executor) {

    constructor(): this(
        DiskIOExecutor(), MainThreadExecutor()
    )

    private class MainThreadExecutor : Executor {
        private val mainThreadHandler: Handler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }
    }
}