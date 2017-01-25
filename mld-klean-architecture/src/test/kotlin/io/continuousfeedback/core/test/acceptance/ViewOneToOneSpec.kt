package io.continuousfeedback.core.test.acceptance

import com.winterbe.expekt.should
import io.continuousfeedback.core.boundary.ContinuousFeedback
import io.continuousfeedback.core.domain.TeamMember
import io.continuousfeedback.core.test.doubles.InMemoryContinuousFeedback
import io.continuousfeedback.core.test.doubles.presenter.RequestFeedbackPresenter
import io.continuousfeedback.core.test.doubles.presenter.ViewNextOneToOnePresenter
import io.continuousfeedback.core.usecase.CreateOneToOne
import io.continuousfeedback.core.usecase.CreateTeamMember
import io.continuousfeedback.core.usecase.RequestFeedback
import io.continuousfeedback.core.usecase.ViewNextOneToOne
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

object ViewOneToOneSpec : Spek({
    val continuousFeedback: ContinuousFeedback = InMemoryContinuousFeedback()

    describe("when retrieving one to one for a team member") {
        var nextOneToOne: ViewNextOneToOne.Presenter.OneToOne? = null

        fun executeCreateTeamMember(email: String) {
            continuousFeedback.executeUseCase(
                    CreateTeamMember::class,
                    CreateTeamMember.Request(email),
                    object : CreateTeamMember.Presenter {
                        override fun onSuccess() {
                        }
                    }
            )
        }

        fun executeCreateOneToOne(date: String, teamMemberId: Int) {
            continuousFeedback.executeUseCase(
                    CreateOneToOne::class,
                    CreateOneToOne.Request(date = date, teamMemberId = teamMemberId),
                    object : CreateOneToOne.Presenter {
                        override fun onSuccess () {}
                    }
            )
        }

        fun executeViewNextOneToOne(teamMemberId: Int) {
            val presenter = ViewNextOneToOnePresenter(
                onSuccess = {
                    nextOneToOne = it
                }
            )

            continuousFeedback.executeUseCase(
                    ViewNextOneToOne::class,
                    ViewNextOneToOne.Request(teamMemberId),
                    presenter
            )
        }

        given("a team member with a one to one") {
            beforeGroup {
                try {
                    executeCreateTeamMember(email = "derek@madetech.com")
                    executeCreateOneToOne(date = "2017-01-01", teamMemberId = 1)
                    executeViewNextOneToOne(1)
                } catch (e: Exception) {
                    print(e)
                }
            }

            it("should return the one to one") {
                nextOneToOne!!.date.should.be.equal("2017-01-01")
            }
        }
    }
})