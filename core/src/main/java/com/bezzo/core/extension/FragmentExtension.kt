package com.bezzo.core.extension

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment

inline fun <reified T : Any> Fragment.launchActivity(
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {

    val intent = newIntent<T>(activity!!)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivity(intent, options)
    } else {
        startActivity(intent)
    }
}

inline fun Fragment.launchFragment(contentReplace: Int, classFragment: Class<*>,
                                            noinline init: Bundle.() -> Unit = {}) {
    var fragment: Fragment? = null

    try {
        fragment = classFragment.newInstance() as Fragment
    } catch (e: InstantiationException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }

    val transaction = activity?.supportFragmentManager?.beginTransaction()

    val data = Bundle()
    data.init()
    fragment?.arguments = data

    fragment?.let { transaction?.replace(contentReplace, it) }

    transaction?.commit()
}