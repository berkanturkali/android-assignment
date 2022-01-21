package com.arabam.android.assignment.database.data.mapper

interface Mapper<Entity, Domain> {

    fun toDomain(entity: Entity?): Domain?

    fun fromDomain(domain: Domain): Entity

    fun toDomainList(list: List<Entity>?): List<Domain>
}
