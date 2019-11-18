package com.bezzo.core.data.session

import com.orhanobut.hawk.Hawk

/**
 * Created by bezzo on 11/01/18.
 */

class SessionHelper {

    fun deleteSession(key: String) {
        Hawk.delete(key)
    }

    fun clearSession() {
        Hawk.deleteAll()
    }
}
