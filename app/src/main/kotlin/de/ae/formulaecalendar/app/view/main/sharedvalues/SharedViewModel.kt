package de.ae.formulaecalendar.app.view.main.sharedvalues

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by aeilers on 18.11.2017.
 */
class SharedViewModel : ViewModel() {
    private val selectedSeason: MutableLiveData<String> = MutableLiveData();

    fun select(season: String){
        selectedSeason.value = season
    }

    fun getSelectedSeason(): LiveData<String>{
        return selectedSeason
    }
}