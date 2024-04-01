package com.example.myapplication.features.allbrandslist.models

data class AllBrandsResponse(
    val status: Int,
    val message: String,
    val data: List<AllBrands>

 )

data class AllBrands(
    val brand_id: Int,
    val brand_name: String,
    val key: String
)