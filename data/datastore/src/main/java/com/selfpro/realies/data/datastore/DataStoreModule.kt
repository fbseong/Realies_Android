package com.selfpro.realies.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.selfpro.realies.util.common.ApplicationScope
import com.selfpro.realies.util.common.Dispatcher
import com.selfpro.realies.util.common.RealiesDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun providesRealiesList(
        @ApplicationContext context: Context,
        @Dispatcher(RealiesDispatcher.IO) ioDispatcher: CoroutineDispatcher,
        @ApplicationScope scope: CoroutineScope,
        preferencesSerializer: RealiesListPreferencesSerializer
//    ){
    ): DataStore<RealiesListPreference> {
       return DataStoreFactory.create(
            serializer = preferencesSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher)
        ) {
            context.dataStoreFile("preferences.pb")
        }
    }
}