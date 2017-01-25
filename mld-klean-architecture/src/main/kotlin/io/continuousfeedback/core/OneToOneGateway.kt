package io.continuousfeedback.core

import io.continuousfeedback.core.domain.OneToOne

interface OneToOneGateway{
    fun findNextForTeamMember(teamMemberId: Int): OneToOne?
    fun save(entity: OneToOne)
}
