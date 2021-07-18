package app.sparsh.projectiniectiohilt.di

import app.sparsh.projectiniectiohilt.repository.MainRepository
import app.sparsh.projectiniectiohilt.retrofit.BlogRetrofit
import app.sparsh.projectiniectiohilt.retrofit.NetworkMapper
import app.sparsh.projectiniectiohilt.room.BlogDao
import app.sparsh.projectiniectiohilt.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, blogRetrofit, cacheMapper, networkMapper)
    }

}