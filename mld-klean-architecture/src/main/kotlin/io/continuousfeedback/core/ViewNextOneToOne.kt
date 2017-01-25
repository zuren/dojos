package io.continuousfeedback.core

import io.continuousfeedback.core.domain.OneToOne
import io.continuousfeedback.core.usecase.ViewNextOneToOne
import io.continuousfeedback.core.usecase.ViewNextOneToOne.Request
import io.continuousfeedback.core.usecase.ViewNextOneToOne.Presenter

class ViewNextOneToOne(val oneToOneGateway: OneToOneGateway) : ViewNextOneToOne {
    override fun execute(request: Request, presenter: Presenter) {
        val oneToOne = oneToOneGateway.findNextForTeamMember(request.teamMemberId)
        val formattedOneToOne = Presenter.OneToOne(date = oneToOne!!.date, teamMemberId = oneToOne!!.teamMemberId)
        presenter.presentOneToOne(formattedOneToOne )
    }
}