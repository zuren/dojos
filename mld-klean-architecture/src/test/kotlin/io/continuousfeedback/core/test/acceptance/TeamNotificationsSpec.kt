package io.continuousfeedback.core.test.acceptance

import com.winterbe.expekt.should
import io.continuousfeedback.core.boundary.ContinuousFeedback
import io.continuousfeedback.core.test.doubles.InMemoryContinuousFeedback
import io.continuousfeedback.core.test.doubles.presenter.RequestFeedbackPresenter
import io.continuousfeedback.core.usecase.CreateTeamMember
import io.continuousfeedback.core.usecase.RequestFeedback
import io.continuousfeedback.core.usecase.RequestFeedback.Presenter.Error.RECEIVER_NOT_FOUND
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

object TeamNotificationsSpec : Spek({
    val continuousFeedback: ContinuousFeedback = InMemoryContinuousFeedback()

    describe("notifying team members about a request for feedback") {
        val notifications: MutableList<RequestFeedback.Presenter.Notification> = mutableListOf()
        val errors: MutableList<RequestFeedback.Presenter.Error> = mutableListOf()
        beforeGroup {
            notifications.clear()
            errors.clear()
        }

        fun executeRequestFeedback(id: Int) {
            val presenter = RequestFeedbackPresenter(
                    onNotify = { notifications.add(it) },
                    onError = { errors.add(it) }
            )
            continuousFeedback.executeUseCase(
                    RequestFeedback::class,
                    RequestFeedback.Request(id),
                    presenter
            )
        }

        fun executeCreateTeamMember(email: String) {
            continuousFeedback.executeUseCase(
                    CreateTeamMember::class,
                    CreateTeamMember.Request(email),
                    object : CreateTeamMember.Presenter {
                        override fun onSuccess() {}
                    }
            )
        }

        given("five team members") {
            val teamMembers: Map<Int, String> = mapOf(
                    Pair(1, "craig@madetech.com"),
                    Pair(2, "rory@madetech.com"),
                    Pair(3, "seb@madetech.com"),
                    Pair(4, "chris@madetech.com"),
                    Pair(5, "scott@madetech.com"),
                    Pair(6, "emile@madetech.com")
            )

            beforeGroup {
                teamMembers.forEach {
                    executeCreateTeamMember(email = it.value)
                }
            }

            context("when feedback receiver id is invalid") {
                beforeGroup { executeRequestFeedback(id = 9001) }
                it("should present an error") { errors.first().should.be.equal(RECEIVER_NOT_FOUND) }
            }

            context("when feedback receiver id is valid") {
                beforeGroup { executeRequestFeedback(id = 1) }
                it("should notify five team members to give feedback") { notifications.size.should.equal(5) }
                it("should have the correct email addresses") {
                    listOf(
                            "rory@madetech.com",
                            "seb@madetech.com",
                            "chris@madetech.com",
                            "scott@madetech.com",
                            "emile@madetech.com"
                    ).forEachIndexed { i, s -> notifications[i].email.should.equal(s) }
                }
            }
        }
    }
})

