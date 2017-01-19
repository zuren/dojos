package io.continuousfeedback.core

import io.continuousfeedback.core.domain.TeamMember

interface TeamGateway {
    fun getAll(): List<TeamMember>
    fun find(id: Int): TeamMember?
    fun save(entity: TeamMember)
}
