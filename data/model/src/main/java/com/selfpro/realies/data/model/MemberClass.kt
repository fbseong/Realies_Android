package com.selfpro.realies.data.model

sealed class MemberClass {
    data object Guest : MemberClass()
    data object RealiesJournalist : MemberClass()
    data object MajorJournalist : MemberClass()
}