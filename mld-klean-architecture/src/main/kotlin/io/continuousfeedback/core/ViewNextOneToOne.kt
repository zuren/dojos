package io.continuousfeedback.core

import io.continuousfeedback.core.usecase.ViewNextOneToOne

class ViewNextOneToOne() : ViewNextOneToOne {
    override fun execute(request: ViewNextOneToOne.Request, presenter: ViewNextOneToOne.Presenter) {
        val oneToOne = ViewNextOneToOne.Presenter.OneToOne(date = "2017-01-01", teamMemberId = request.teamMemberId)
        presenter.presentOneToOne(oneToOne)
    }
}