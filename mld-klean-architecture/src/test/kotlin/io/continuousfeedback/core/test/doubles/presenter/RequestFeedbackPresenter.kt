package io.continuousfeedback.core.test.doubles.presenter

import io.continuousfeedback.core.usecase.RequestFeedback.Presenter

class RequestFeedbackPresenter(val onNotify: (Presenter.Notification) -> Unit,
                               val onError: (Presenter.Error) -> Unit) : Presenter {

    override fun presentNotification(notification: Presenter.Notification) = onNotify(notification)
    override fun presentError(error: Presenter.Error) = onError(error)
}