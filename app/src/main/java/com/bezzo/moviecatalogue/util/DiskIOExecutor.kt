package com.bezzo.moviecatalogue.util

import java.util.concurrent.Executor
import java.util.concurrent.Executors

class DiskIOExecutor: Executor {

    private var mDiskIO: Executor = Executors.newSingleThreadExecutor()

    override fun execute(command: Runnable) {
        mDiskIO.execute(command)
    }
}