package io.continuousfeedback.core.test.doubles.gateway

import com.madetech.clean.test.doubles.InMemorySimplisticRepository
import io.continuousfeedback.core.OneToOneGateway
import io.continuousfeedback.core.TeamGateway
import io.continuousfeedback.core.domain.OneToOne
import io.continuousfeedback.core.domain.TeamMember

class InMemoryOneToOneRepository : OneToOneGateway, InMemorySimplisticRepository<OneToOne>() {
    override fun findNextForTeamMember(teamMemberId: Int): OneToOne? {
        entities.forEach { if (teamMemberId == it.teamMemberId) return it }
        return null
    }
}

