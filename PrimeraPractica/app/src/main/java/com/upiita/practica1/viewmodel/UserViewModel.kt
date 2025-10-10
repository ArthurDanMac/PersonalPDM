package com.upiita.practica1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upiita.practica1.model.UserModel
import com.upiita.practica1.model.UserProvider

class UserViewModel : ViewModel() {
    companion object {
        var userModelMutableList = MutableLiveData<UserModel>()

    }
}