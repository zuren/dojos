package io.continuousfeedback.core.test.unit

import com.winterbe.expekt.should
import io.continuousfeedback.core.RequestFeedback
import io.continuousfeedback.core.ViewNextOneToOne
import io.continuousfeedback.core.domain.TeamMember
import io.continuousfeedback.core.test.doubles.gateway.InMemoryOneToOneRepository
import io.continuousfeedback.core.test.doubles.gateway.InMemoryTeamRepository
import io.continuousfeedback.core.test.doubles.presenter.ViewNextOneToOnePresenter
import io.continuousfeedback.core.usecase.ViewNextOneToOne.Request
import io.continuousfeedback.core.usecase.ViewNextOneToOne.Presenter
import io.continuousfeedback.core.domain.OneToOne
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek


class ViewNextOneToOneSpec : SubjectSpek<ViewNextOneToOne>({
    var oneToOne: Presenter.OneToOne? = null

    beforeEachTest {
        oneToOne = null
    }

    val oneToOneGateway = InMemoryOneToOneRepository()
    subject { ViewNextOneToOne(oneToOneGateway) }


    fun execute(teamMemberId: Int) {
        val presenter = ViewNextOneToOnePresenter(
                onSuccess = { oneToOne = it }
        )
        subject.execute(Request(teamMemberId = teamMemberId), presenter)
    }

    val teamMemberId = 1
    beforeEachTest { execute(teamMemberId) }

    given("a team member has a one to one scheduled") {
        beforeGroup {
            oneToOneGateway.save(OneToOne(id = 1, date = "2017-01-01", teamMemberId = teamMemberId))
        }

        it("should return the next scheduled one to one for the team member") {
            oneToOne!!.teamMemberId.should.be.equal(teamMemberId)
        }
    }
})
