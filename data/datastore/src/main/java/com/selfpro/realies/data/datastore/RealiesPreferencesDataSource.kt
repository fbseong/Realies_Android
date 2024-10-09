package com.selfpro.realies.data.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RealiesPreferencesDataSource @Inject constructor(
    private val realiesListPreference: DataStore<RealiesListPreference>
) {
    val title = realiesListPreference.data.map { it.title }

    suspend fun saveTitle(title: String) {
        realiesListPreference.updateData {
            it.copy {
                this.title = title
            }
        }
    }
}