package io.continuousfeedback.core.test.doubles.presenter

import io.continuousfeedback.core.usecase.RequestFeedback.*

class RequestFeedbackPresenter(val onNotify: (Notification) -> Unit,
                               val onError: (Error) -> Unit) : Presenter {

    override fun presentNotification(notification: Notification) = onNotify(notification)
    override fun presentError(error: Error) = onError(error)
}