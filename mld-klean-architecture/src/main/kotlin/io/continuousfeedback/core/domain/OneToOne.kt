package io.continuousfeedback.core.domain

import com.madetech.clean.domain.OptionallyIdentified

class OneToOne(id: Int?, val date: String, val teamMemberId: Int) : OptionallyIdentified(id)