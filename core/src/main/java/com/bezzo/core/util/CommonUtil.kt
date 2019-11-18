package com.bezzo.core.util

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by bezzo on 26/09/17.
 */

object CommonUtil {

    fun isJSONValid(test: String?): Boolean {

        if (test == null || test.isEmpty()) {
            return false
        }

        try {
            JSONObject(test)
        } catch (ex: JSONException) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                JSONArray(test)
            } catch (ex1: JSONException) {
                return false
            }

        }

        return true
    }
}// this utility class is not publicy instantiable
