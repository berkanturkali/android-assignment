package com.arabam.android.assignment.mapper.base

interface RemoteModelMapper<in M, out D> {

    fun mapFromModel(model: M): D

    fun mapModelList(models: List<M>): List<D> {
        return models.mapTo(mutableListOf(), ::mapFromModel)
    }
}
