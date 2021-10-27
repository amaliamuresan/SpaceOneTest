package com.example.spaceonetest

import com.example.spaceonetest.models.Category
import com.example.spaceonetest.presentation.viewmodels.OverviewViewModel

fun getCategoryIdByName(name: String): String {
    val category = OverviewViewModel.categories.value!!.find {
        it.name == name
    }
    return category!!.id
}

fun getCategoryById(id: String): Category {
    val category = OverviewViewModel.categories.value!!.find {
        it.id == id
    }
    return category!!
}