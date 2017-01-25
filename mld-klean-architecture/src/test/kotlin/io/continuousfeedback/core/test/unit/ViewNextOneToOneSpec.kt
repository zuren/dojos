package io.continuousfeedback.core.test.unit

import com.winterbe.expekt.should
import io.continuousfeedback.core.ViewNextOneToOne
import io.continuousfeedback.core.domain.TeamMember
import io.continuousfeedback.core.test.doubles.presenter.ViewNextOneToOnePresenter
import io.continuousfeedback.core.usecase.ViewNextOneToOne.Request
import io.continuousfeedback.core.usecase.ViewNextOneToOne.Presenter.OneToOne
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek


class ViewNextOneToOneSpec : SubjectSpek<ViewNextOneToOne>({
    var oneToOne: OneToOne? = null

    beforeEachTest {
        oneToOne = null
    }

    subject { ViewNextOneToOne() }

    fun execute(teamMemberId: Int) {
        val presenter = ViewNextOneToOnePresenter(
                onSuccess = { oneToOne = it }
        )
        subject.execute(Request(teamMemberId = teamMemberId), presenter)
    }

    val teamMemberId = 1
    beforeEachTest { execute(teamMemberId) }

    given("a team member has a one to one scheduled") {
        it("should return the next scheduled one to one for the team member") {
            oneToOne!!.teamMemberId.should.be.equal(teamMemberId)
        }
    }
})
