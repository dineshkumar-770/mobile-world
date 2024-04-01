package com.example.myapplication.features.brand_devices.service

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.features.brand_devices.models.BrandDevicesResponse
import com.example.myapplication.features.brand_devices.models.DeviceListRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class BrandDevicesManager: ViewModel() {
    private val _brandDevicesState = mutableStateOf(BrandDevicesDataState())
    val brandDevicesState: State<BrandDevicesDataState> = _brandDevicesState

    data class BrandDevicesDataState(
        val loading: Boolean = false,
        val error: String?=null,
        val data: BrandDevicesResponse?= null
    )

    fun fetchBrandDevicesData(deviceListRequest: DeviceListRequest){
        _brandDevicesState.value = _brandDevicesState.value.copy(
            loading = true
        )
        try {
            val myResponse = BrandDevicesRetrofit.retroFitService.getBrandDevices(deviceListRequest)
            myResponse.enqueue(object: Callback<BrandDevicesResponse>{
                override fun onResponse(
                    call: Call<BrandDevicesResponse>,
                    response: Response<BrandDevicesResponse>
                ) {
                    if(response.isSuccessful){
                        _brandDevicesState.value = _brandDevicesState.value.copy(
                            loading = false,
                            data = response.body(),
                            error = null
                        )
                    }else{
                        _brandDevicesState.value = _brandDevicesState.value.copy(
                            loading = false,
                            data = null,
                            error = response.errorBody().toString()
                        )
                    }
                }

                override fun onFailure(call: Call<BrandDevicesResponse>, t: Throwable) {
                    _brandDevicesState.value = _brandDevicesState.value.copy(
                        loading = false,
                        data = null,
                        error = t.message.toString()
                    )
                }

            })
        } catch (e:Exception){
            _brandDevicesState.value = _brandDevicesState.value.copy(
                loading = false,
                data = null,
                error = e.message.toString()
            )
        }
    }
}