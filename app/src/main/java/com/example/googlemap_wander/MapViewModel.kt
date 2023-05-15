package com.example.googlemap_wander

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.googlemap_wander.Models.Place
import com.example.googlemap_wander.Models.UserMap

class MapViewModel :  ViewModel() {
    private val _myData = MutableLiveData<MutableList<UserMap>>().apply { value = generateSampleData() }
    val myData: LiveData<MutableList<UserMap>>
        get() = _myData

    fun addMap(userMap:UserMap){
        val tempList = _myData.value
        tempList?.add(userMap)
        _myData.postValue(tempList)


    }
//    fun addPlace(place:Place) {
//        val position = _myData.value?.size?.minus(1)
//        if (position != null) {
//            _myData.value?.get(position)?.places?.add(place)
//        }
//    }

    private fun generateSampleData(): MutableList<UserMap> {
        return mutableListOf(
            UserMap(
                "Memories from University",
                mutableListOf(
                    Place("Branner Hall", "Best dorm at Stanford", 37.426, -122.163),
                    Place("Gates CS building", "Many long nights in this basement", 37.430, -122.173),
                    Place("Pinkberry", "First date with my wife", 37.444, -122.170)
                )
            ),
            UserMap("January vacation planning!",
                mutableListOf(
                    Place("Tokyo", "Overnight layover", 35.67, 139.65),
                    Place("Ranchi", "Family visit + wedding!", 23.34, 85.31),
                    Place("Singapore", "Inspired by \"Crazy Rich Asians\"", 1.35, 103.82)
                )),
        )
    }
    

}