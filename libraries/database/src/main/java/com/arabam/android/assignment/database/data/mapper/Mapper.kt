package com.arabam.android.assignment.database.data.mapper

public interface Mapper<Entity, Domain> {

    public fun toDomain(entity: Entity?): Domain?

    public fun fromDomain(domain: Domain): Entity

    public fun toDomainList(list: List<Entity>?): List<Domain>
}
