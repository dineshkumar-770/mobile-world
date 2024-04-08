package com.example.myapplication.features.device_details.service

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.features.device_details.models.DeviceDetailsRequestBody
import com.example.myapplication.features.device_details.models.DeviceDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class DeviceDetailsManager: ViewModel() {
    private val _deviceDetailsState = mutableStateOf(DeviceDetailsState())
    val deviceDetails: State<DeviceDetailsState> = _deviceDetailsState

    data class DeviceDetailsState(
        val loading: Boolean = false,
        val error: String? = null,
        val data: DeviceDetailsResponse? = null
    )

    fun fetchDeviceDetails(requestBody: DeviceDetailsRequestBody) {
        _deviceDetailsState.value = _deviceDetailsState.value.copy(
            loading = true
        )

       try {
           val myReponse = DeviceDetailsRetrofit.retroFitService.getDeviceDetails(requestBody)

           myReponse.enqueue(object : Callback<DeviceDetailsResponse> {
               override fun onResponse(
                   call: Call<DeviceDetailsResponse>,
                   response: Response<DeviceDetailsResponse>
               ) {
                   if(response.isSuccessful){
                       _deviceDetailsState.value = _deviceDetailsState.value.copy(
                           loading = false,
                           error = null,
                           data = response.body()
                       )
                   }else{
                       _deviceDetailsState.value = _deviceDetailsState.value.copy(
                           loading = false,
                           data = null,
                           error = response.errorBody().toString()
                       )
                   }
               }

               override fun onFailure(call: Call<DeviceDetailsResponse>, t: Throwable) {
                   _deviceDetailsState.value = _deviceDetailsState.value.copy(
                       loading = false,
                       data = null,
                       error = t.message.toString()
                   )
               }

           })
       } catch (e:Exception){
           _deviceDetailsState.value = _deviceDetailsState.value.copy(
               loading = false,
               data = null,
               error = e.message.toString()
           )
       }
    }
}