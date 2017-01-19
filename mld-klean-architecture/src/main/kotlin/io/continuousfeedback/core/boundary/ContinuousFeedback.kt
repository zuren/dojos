package io.continuousfeedback.core.boundary

import com.madetech.clean.boundary.CleanApplication
import com.madetech.clean.usecase.AsynchronousUseCase
import io.continuousfeedback.core.TeamGateway
import io.continuousfeedback.core.usecase.CreateTeamMember
import io.continuousfeedback.core.usecase.RequestFeedback
import kotlin.reflect.KClass

abstract class ContinuousFeedback : CleanApplication() {
    abstract val teamGateway: TeamGateway

    @Suppress("UNCHECKED_CAST")
    override fun <P, R, U : AsynchronousUseCase<R, P>> unsafeConstruct(useCase: KClass<in U>): AsynchronousUseCase<R, P>? {
        return when (useCase) {
            RequestFeedback::class -> io.continuousfeedback.core.RequestFeedback(teamGateway)
            CreateTeamMember::class -> io.continuousfeedback.core.CreateTeamMember(teamGateway)
            else -> null
        } as AsynchronousUseCase<R, P>?
    }
}