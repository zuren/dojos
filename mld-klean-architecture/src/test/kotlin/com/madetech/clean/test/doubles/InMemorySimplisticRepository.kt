package com.madetech.clean.test.doubles

import com.madetech.clean.domain.OptionallyIdentified

abstract class InMemorySimplisticRepository<T : OptionallyIdentified> {
    var id: Int = 1
    val entities: MutableList<T> = mutableListOf()

    fun find(id: Int): T? {
        entities.forEach { if (id == it.id) return it }
        return null
    }

    fun getAll(): List<T> = entities.toList()

    fun save(entity: T) {
        if(entity.id == null) entity.id = id++
        entities.add(entity)
    }

    fun deleteAll() = entities.clear()
}