package io.continuousfeedback.core.test.unit

import com.winterbe.expekt.should
import io.continuousfeedback.core.RequestFeedback
import io.continuousfeedback.core.domain.TeamMember
import io.continuousfeedback.core.test.doubles.InMemoryTeamGateway
import io.continuousfeedback.core.test.doubles.presenter.RequestFeedbackPresenter
import io.continuousfeedback.core.usecase.RequestFeedback.Error
import io.continuousfeedback.core.usecase.RequestFeedback.Error.RECEIVER_NOT_FOUND
import io.continuousfeedback.core.usecase.RequestFeedback.Notification
import io.continuousfeedback.core.usecase.RequestFeedback.Request
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek


class RequestFeedbackSpec : SubjectSpek<RequestFeedback>({
    val notificationsSent: MutableList<Notification> = mutableListOf()
    val errorsPresented: MutableList<Error> = mutableListOf()
    beforeEachTest {
        errorsPresented.clear()
        notificationsSent.clear()
    }

    val teamGateway = InMemoryTeamGateway()
    subject { RequestFeedback(teamGateway) }

    fun execute(feedbackReceiverId: Int) {
        val presenter = RequestFeedbackPresenter(
                onNotify = { notificationsSent.add(it) },
                onError = { errorsPresented.add(it) }
        )
        subject.execute(Request(feedbackReceiverId), presenter)
    }

    val feedbackReceiverId = 1337
    beforeEachTest { execute(feedbackReceiverId) }

    given("feedback receiver does not exist") {
        it("should present receiver not found error") {
            errorsPresented.first().should.equal(RECEIVER_NOT_FOUND)
        }

        given("one other team member") {
            beforeGroup { teamGateway.save(TeamMember(3, "craig@madetech.com")) }
            it("should not notify them") {
                notificationsSent.size.should.be.equal(0)
            }
        }
    }

    given("feedback receiver exists") {
        beforeGroup {
            teamGateway.deleteAll()
            teamGateway.save(TeamMember(id = feedbackReceiverId, email = "iwantfeedback@example.com"))
        }

        it("should not present any errors") {
            errorsPresented.size.should.be.equal(0)
        }

        given("no other team members") {
            it("should send 0 notifications") { notificationsSent.size.should.equal(0) }
        }

        given("one other team member") {
            beforeGroup { teamGateway.save(TeamMember(0, "craig@madetech.com")) }
            it("should send 1 notification") { notificationsSent.size.should.equal(1) }
            it("should have the correct email address") {
                notificationsSent[0].email.should.equal("craig@madetech.com")
            }

            given("two other team members") {
                beforeGroup { teamGateway.save(TeamMember(0, "rory@madetech.com")) }
                it("should send 2 notifications") { notificationsSent.size.should.equal(2) }
                it("should have the correct email address") {
                    notificationsSent[1].email.should.equal("rory@madetech.com")
                }
            }
        }
    }
})
