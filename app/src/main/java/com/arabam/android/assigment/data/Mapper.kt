package com.arabam.android.assigment.data

interface Mapper<Entity,Domain> {

    fun toDomain(entity:Entity?):Domain?

    fun fromDomain(domain:Domain):Entity

    fun toDomainList(list:List<Entity>?):List<Domain>
}