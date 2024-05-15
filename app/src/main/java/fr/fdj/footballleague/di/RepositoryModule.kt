package fr.fdj.footballleague.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import fr.fdj.footballleague.api.service.LeagueService
import fr.fdj.footballleague.repository.LeagueRepository
import fr.fdj.footballleague.repository.LeagueRepositoryImpl

@Module(includes = [ApiModule::class])
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    fun provideLeagueRepository(leagueService: LeagueService): LeagueRepository {
        return LeagueRepositoryImpl(leagueService)
    }
}