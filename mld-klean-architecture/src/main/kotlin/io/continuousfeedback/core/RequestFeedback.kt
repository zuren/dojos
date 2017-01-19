package io.continuousfeedback.core

import io.continuousfeedback.core.domain.TeamMember
import io.continuousfeedback.core.usecase.RequestFeedback
import io.continuousfeedback.core.usecase.RequestFeedback.Presenter
import io.continuousfeedback.core.usecase.RequestFeedback.Presenter.Error.RECEIVER_NOT_FOUND
import io.continuousfeedback.core.usecase.RequestFeedback.Request

class RequestFeedback(val teamGateway: TeamGateway) : RequestFeedback {
    override fun execute(request: Request, presenter: Presenter) {
        val id = request.receiverId

        if (teamGateway.find(id = id) == null) {
            presenter.presentError(RECEIVER_NOT_FOUND)
            return
        }

        teamGateway.getAllExceptFeedbackReceiver(receiverId = id)
                .sendNotificationsForAlLTo(presenter)
    }

    fun List<TeamMember>.sendNotificationsForAlLTo(presenter: Presenter) = this.forEach { presenter.notify(it.email) }

    fun TeamGateway.getAllExceptFeedbackReceiver(receiverId: Int) = this.getAll().filter { it.id != receiverId }

    fun Presenter.notify(email: String) = this.presentNotification(Presenter.Notification(email = email))
}
