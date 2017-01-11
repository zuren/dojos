package io.continuousfeedback.core

import io.continuousfeedback.core.domain.TeamMember

interface TeamGateway {
    fun getAll(): List<TeamMember>
    fun find(teamMemberId: Int): TeamMember?
}
