package com.andrei.hearthstoneassessment

import dagger.hilt.android.testing.CustomTestApplication
import com.andrei.hearthstoneassessment.presentation.AbstractApplication


@CustomTestApplication(AbstractApplication::class)
interface CustomTestApplicationForHilt