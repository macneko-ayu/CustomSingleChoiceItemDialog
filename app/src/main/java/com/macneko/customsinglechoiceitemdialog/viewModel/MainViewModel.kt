package com.macneko.customsinglechoiceitemdialog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {
  private val _disableIndex = MutableLiveData(INVALID_INDEX)
  val disableIndex: LiveData<Int>
    get() = _disableIndex

  private val _selectedIndex = MutableLiveData(INVALID_INDEX)
  val selectedIndex: LiveData<Int>
    get() = _selectedIndex

  private val _disabledItemValue = MutableLiveData("")
  val disabledItemValue: LiveData<String>
    get() = _disabledItemValue

  private val _selectedItemValue = MutableLiveData("")
  val selectedItemValue: LiveData<String>
    get() = _selectedItemValue

  fun setDisableIndex(value: Int) {
    _disableIndex.value = value
  }

  fun setSelectedIndex(value: Int) {
    _selectedIndex.value = value
  }

  fun setDisabledItemValue(value: String) {
    _disabledItemValue.value = value
  }

  fun setSelectedItemValue(value: String) {
    _selectedItemValue.value = value
  }

  companion object {
    const val INVALID_INDEX = -1
  }
}