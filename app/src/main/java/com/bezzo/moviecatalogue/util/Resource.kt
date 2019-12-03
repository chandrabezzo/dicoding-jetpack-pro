package com.bezzo.moviecatalogue.util

class Resource<T>(val status: Status,
                  val data: T?,
                  val message: String?) {

    fun <T> success(data: T?): Resource<T?>? {
        return Resource(Status.SUCCESS, data, null)
    }

    fun <T> error(msg: String?, data: T?): Resource<T?>? {
        return Resource(Status.ERROR, data, msg)
    }

    fun loading(data: T?): Resource<T?> {
        return Resource(Status.LOADING, data, null)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val resource =
            o as Resource<*>
        if (status !== resource.status) {
            return false
        }
        return if (if (message != null) message != resource.message else resource.message != null) {
            false
        } else data?.equals(resource.data) ?: (resource.data == null)
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (message?.hashCode() ?: 0)
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Resource{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}'
    }
}