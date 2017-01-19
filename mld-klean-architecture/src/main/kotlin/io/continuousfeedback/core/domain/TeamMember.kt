package io.continuousfeedback.core.domain

import com.madetech.clean.domain.OptionallyIdentified

class TeamMember(id: Int?, val email: String) : OptionallyIdentified(id)