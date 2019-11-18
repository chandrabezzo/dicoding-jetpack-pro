package com.bezzo.core.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bezzo.core.R

inline fun <reified T : Any> Activity.launchActivityClearAllStack(noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this)
    intent.init()
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(intent)
    overridePendingTransition(R.anim.slide_in_from_right, R.anim.scale_out)
    finish()
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
        Intent(context, T::class.java)

inline fun AppCompatActivity.launchFragment(contentReplace: Int, classFragment: Class<*>,
                                            noinline init: Bundle.() -> Unit = {}){
    var fragment: Fragment? = null

    try {
        fragment = classFragment.newInstance() as Fragment
    } catch (e: InstantiationException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }

    val transaction = this.supportFragmentManager.beginTransaction()

    val data = Bundle()
    data.init()
    fragment?.arguments = data

    fragment?.let { transaction.replace(contentReplace, it) }

    transaction.commit()
}