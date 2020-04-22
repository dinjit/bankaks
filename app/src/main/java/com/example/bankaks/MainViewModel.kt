package com.example.bankaks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.bankaks.model.Creds
import com.example.bankaks.model.Recharge
import com.momentsnap.android.Event
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val restApi: RestApi) : BaseViewModel() {

    private val credentials = MutableLiveData<Creds>()
    private val recharge = MutableLiveData<Recharge>()
    private val _coupon = MutableLiveData<String>()

    var loginResponse = credentials.switchMap { creds ->
        liveData(Dispatchers.IO) {
            try {
                val response = restApi.login(creds)
                emit(response)
            } catch (e: Exception) {
                error.postValue(Event(e.localizedMessage))
                Timber.d(e)
            }

        }
    }

    var rechargeCoupon = recharge.switchMap { recharge ->
        liveData(Dispatchers.IO) {
            try {
                val response = restApi.recharge(recharge)
                _coupon.postValue(response.rechargeCoupon)
                emit(Event(response))
            } catch (e: Exception) {
                error.postValue(Event(e.localizedMessage))
                Timber.d(e)
            }
        }
    }

    val coupon: LiveData<String>
        get() = _coupon

    fun login(creds: Creds) {
        credentials.value = creds
    }

    fun getRechargeCoupon(recharge: Recharge) {
        this.recharge.value = recharge
    }

}