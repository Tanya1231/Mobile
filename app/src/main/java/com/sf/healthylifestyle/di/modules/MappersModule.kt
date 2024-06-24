package ed.maevski.imdb.di.modules

import com.sf.healthylifestyle.data.mappers.dish.DishMapper
import com.sf.healthylifestyle.data.mappers.dish.IDishMapper
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface MappersModule {
    @Binds
    @Singleton
    fun bindNodesMapper(mapper: DishMapper): IDishMapper
}