package com.example.livedataexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecordViewModel: ViewModel() {
    val records: MutableLiveData<Int>
        get() {
            return records
        }
}