package app.sparsh.projectiniectiohilt.repository

import app.sparsh.projectiniectiohilt.model.Blog
import app.sparsh.projectiniectiohilt.retrofit.BlogRetrofit
import app.sparsh.projectiniectiohilt.retrofit.NetworkMapper
import app.sparsh.projectiniectiohilt.room.BlogDao
import app.sparsh.projectiniectiohilt.room.CacheMapper
import app.sparsh.projectiniectiohilt.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MainRepository
@Inject
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        kotlinx.coroutines.delay(1000)
        try {
            val networkBlogs = blogRetrofit.getBlogs()
            val blogs = networkMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.getAllBlogs()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}