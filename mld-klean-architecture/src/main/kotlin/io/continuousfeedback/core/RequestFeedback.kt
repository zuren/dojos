package io.continuousfeedback.core

import io.continuousfeedback.core.usecase.RequestFeedback
import io.continuousfeedback.core.usecase.RequestFeedback.*
import io.continuousfeedback.core.usecase.RequestFeedback.Error.RECEIVER_NOT_FOUND

class RequestFeedback(val teamGateway: TeamGateway) : RequestFeedback {
    override fun execute(request: Request, presenter: Presenter) {
        if( teamGateway.find(request.receiverId) == null ) {
            presenter.presentError(RECEIVER_NOT_FOUND)
            return
        }
        teamGateway.all().filter { it.id != request.receiverId }.forEach { teamMember ->
            presenter.presentNotification(Notification(email = teamMember.email))
        }
    }
}
