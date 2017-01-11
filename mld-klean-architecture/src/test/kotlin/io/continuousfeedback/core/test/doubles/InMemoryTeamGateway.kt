package io.continuousfeedback.core.test.doubles

import io.continuousfeedback.core.TeamGateway
import io.continuousfeedback.core.domain.TeamMember

class InMemoryTeamGateway : TeamGateway {
    val teamMembers: MutableList<TeamMember> = mutableListOf()

    override fun find(teamMemberId: Int):TeamMember? {
        teamMembers.forEach { if(teamMemberId == it.id) return it }
        return null
    }

    override fun all(): List<TeamMember> = teamMembers.toList()

    fun save(teamMember: TeamMember) {
        teamMembers.add(teamMember)
    }

    fun deleteAll() = teamMembers.clear()
}