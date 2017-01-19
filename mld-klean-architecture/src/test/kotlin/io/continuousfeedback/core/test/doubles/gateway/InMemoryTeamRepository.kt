package io.continuousfeedback.core.test.doubles.gateway

import com.madetech.clean.test.doubles.InMemorySimplisticRepository
import io.continuousfeedback.core.TeamGateway
import io.continuousfeedback.core.domain.TeamMember

class InMemoryTeamRepository : TeamGateway, InMemorySimplisticRepository<TeamMember>()

