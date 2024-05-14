package fr.fdj.footballleague.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import fr.fdj.footballleague.api.service.LeagueService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


/**
 * Activity which displays the main screen of the application, which consists of a search form and
 * the list of teams matching the typed league in the form.
 */
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO: Replace by DI as a first step, and make the call in the viewmodel as a second step
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/50130162/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val leagueService = retrofit.create<LeagueService>()

        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }

        GlobalScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            Log.v("FDJ", "${leagueService.getAllLeagues()}")
        }
    }
}