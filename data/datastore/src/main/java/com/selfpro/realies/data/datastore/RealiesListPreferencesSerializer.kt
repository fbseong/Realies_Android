package com.selfpro.realies.data.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class RealiesListPreferencesSerializer @Inject constructor() : Serializer<RealiesListPreference> {
    override val defaultValue: RealiesListPreference = RealiesListPreference.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): RealiesListPreference {
        try {
            return RealiesListPreference.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: RealiesListPreference, output: OutputStream) =
        t.writeTo(output)
}