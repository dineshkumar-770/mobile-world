package com.example.myapplication.features.allbrandslist.service

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.features.allbrandslist.models.AllBrands
import com.example.myapplication.features.allbrandslist.models.AllBrandsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class AllBrandsManager: ViewModel(){
    private val _allBrandsState = mutableStateOf(AllBrandsDataState())
    val allBrandsP: State<AllBrandsDataState> = _allBrandsState

    data class AllBrandsDataState(
        val loading: Boolean = true,
        val data: List<AllBrands>? = emptyList(),
        val error: String? = null
    )

    init {
        fetchAllBrands()
    }

    private fun fetchAllBrands(){
        try {
            val myResponse = AllBrandsRetrofit.retrofitService.getAllBrands("brand-list")
            myResponse.enqueue(object : Callback<AllBrandsResponse>{
                override fun onResponse(
                    call: Call<AllBrandsResponse>,
                    response: Response<AllBrandsResponse>
                ) {
                    if(response.isSuccessful){
                        _allBrandsState.value = _allBrandsState.value.copy(
                            loading = false,
                            data =  response.body()!!.data,
                            error = response.errorBody().toString()
                        )
                    }else{
                        _allBrandsState.value =  _allBrandsState.value.copy(
                            loading = false,
                            data = emptyList(),
                            error = response.errorBody().toString()
                        )
                    }
                }

                override fun onFailure(call: Call<AllBrandsResponse>, t: Throwable) {
                    _allBrandsState.value = _allBrandsState.value.copy(
                        loading = false,
                        data = emptyList(),
                        error = t.message.toString()
                    )
                }

            })
        } catch (e: Exception){
            _allBrandsState.value = _allBrandsState.value.copy(
                loading = false,
                data = null,
                error = e.message.toString()
            )

        }
    }
}