package io.continuousfeedback.core

import io.continuousfeedback.core.domain.OneToOne
import io.continuousfeedback.core.usecase.CreateOneToOne

class CreateOneToOne(val oneToOneGateway: OneToOneGateway) : CreateOneToOne {
    override fun execute(request: CreateOneToOne.Request, presenter: CreateOneToOne.Presenter) {
        oneToOneGateway.save(OneToOne(id = null, date = request.date, teamMemberId = request.teamMemberId))
        presenter.onSuccess()
    }
}
