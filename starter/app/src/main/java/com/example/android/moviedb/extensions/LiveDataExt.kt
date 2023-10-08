package com.example.android.moviedb.extensions

import androidx.lifecycle.MutableLiveData
import com.example.android.moviedb.ultis.Resource

operator fun <T> MutableLiveData<MutableList<T>>.plusAssign(item: T) {
    val value = this.value ?: mutableListOf()
    value.add(item)
    this.value = value
}

operator fun <T> MutableLiveData<MutableList<T>>.plusAssign(item: MutableList<T>) {
    val value = this.value ?: mutableListOf()
    value.addAll(item)
    this.postValue(value)
}

fun <T> MutableLiveData<Resource<MutableList<T>>>.plusAssignResource(items: MutableList<T>) {
    val value = this.value?.data ?: mutableListOf()
    value.addAll(items)
    this.postValue(Resource.success(value))
}
