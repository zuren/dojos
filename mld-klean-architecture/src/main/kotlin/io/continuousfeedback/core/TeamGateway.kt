package io.continuousfeedback.core

import io.continuousfeedback.core.domain.TeamMember

interface TeamGateway {
    fun all(): List<TeamMember>
    fun find(teamMemberId: Int): TeamMember?
}
