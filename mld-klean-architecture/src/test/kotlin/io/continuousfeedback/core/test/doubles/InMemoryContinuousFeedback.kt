package io.continuousfeedback.core.test.doubles

import io.continuousfeedback.core.OneToOneGateway
import io.continuousfeedback.core.TeamGateway
import io.continuousfeedback.core.boundary.ContinuousFeedback
import io.continuousfeedback.core.test.doubles.gateway.InMemoryOneToOneRepository
import io.continuousfeedback.core.test.doubles.gateway.InMemoryTeamRepository

class InMemoryContinuousFeedback : ContinuousFeedback() {
    override val teamGateway: TeamGateway = InMemoryTeamRepository()
    override val oneToOneGateway: OneToOneGateway = InMemoryOneToOneRepository()
}