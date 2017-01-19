package io.continuousfeedback.core.test.unit

import com.winterbe.expekt.should
import io.continuousfeedback.core.CreateTeamMember

import io.continuousfeedback.core.TeamGateway
import io.continuousfeedback.core.test.doubles.gateway.InMemoryTeamRepository
import io.continuousfeedback.core.usecase.CreateTeamMember.Presenter
import io.continuousfeedback.core.usecase.CreateTeamMember.Request
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek

class CreateTeamMemberSpec : SubjectSpek<CreateTeamMember>({
    val teamGateway: TeamGateway = InMemoryTeamRepository()
    subject { CreateTeamMember(teamGateway) }

    given("a valid email address") {
        var successful: Boolean? = null
        beforeGroup {
            subject.execute(Request("craig@madetech.com"), object : Presenter {
                override fun onSuccess() {
                    successful = true
                }
            })
        }
        it("saves a team member in the gateway") {
            teamGateway.getAll().size.should.be.equal(1)
        }
        it("saves with correct email") {
            teamGateway.find(1)!!.email.should.be.equal("craig@madetech.com")
        }
        it("notifies the presenter that it was successful") {
            successful.should.be.`true`
        }
    }
})


