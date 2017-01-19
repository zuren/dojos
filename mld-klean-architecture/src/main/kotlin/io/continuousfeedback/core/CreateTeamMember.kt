package io.continuousfeedback.core

import io.continuousfeedback.core.domain.TeamMember
import io.continuousfeedback.core.usecase.CreateTeamMember

class CreateTeamMember(val teamGateway: TeamGateway) : CreateTeamMember {
    override fun execute(request: CreateTeamMember.Request, presenter: CreateTeamMember.Presenter) {
        teamGateway.save(TeamMember(null, request.email))
        presenter.onSuccess()
    }
}
