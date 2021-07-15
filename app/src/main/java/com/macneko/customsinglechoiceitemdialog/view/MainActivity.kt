package com.macneko.customsinglechoiceitemdialog.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.get
import com.macneko.customsinglechoiceitemdialog.R
import com.macneko.customsinglechoiceitemdialog.databinding.ActivityMainBinding
import com.macneko.customsinglechoiceitemdialog.viewModel.MainViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {
  private val binding: ActivityMainBinding by lazy {
    ActivityMainBinding.bind(findViewById<ViewGroup>(android.R.id.content)[0])
  }
  private val viewModel by viewModels<MainViewModel>()
  private val dialogValues: List<String>
    get() = resources.getStringArray(R.array.dialog_values).toList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding.lifecycleOwner = this
    binding.viewModel = viewModel

    supportFragmentManager.setFragmentResultListener(REQUEST_KEY_NORMAL, this) { _, bundle ->
      val disableIndex =
        bundle.getInt(NormalSingleChoiceItemDialogFragment.RESULT_KEY, MainViewModel.INVALID_INDEX)
      viewModel.setDisableIndex(disableIndex)
      viewModel.setDisabledItemValue(getString(R.string.disabled_item_message, disableIndex))
    }
    supportFragmentManager.setFragmentResultListener(REQUEST_KEY_CUSTOM, this) { _, bundle ->
      val selectedIndex =
        bundle.getInt(CustomSingleChoiceItemDialogFragment.RESULT_KEY, MainViewModel.INVALID_INDEX)
      viewModel.setSelectedIndex(selectedIndex)
      val selectedItemValue = if (selectedIndex > -1) {
        dialogValues[selectedIndex]
      } else {
        getString(R.string.not_selected_item_message)
      }
      viewModel.setSelectedItemValue(selectedItemValue)
    }

    initViews()
  }

  private fun initViews() {
    viewModel.setSelectedItemValue(getString(R.string.not_selected_item_message))
    viewModel.setDisabledItemValue(getString(R.string.not_selected_item_message))
    binding.normalDialogButton.setOnClickListener {
      showNormalDialog()
    }
    binding.customDialogButton.setOnClickListener {
      showCustomDialog()
    }
  }

  private fun showCustomDialog() {
    val dialog = CustomSingleChoiceItemDialogFragment.newInstance(
      getString(R.string.custom_dialog_text),
      dialogValues,
      viewModel.selectedIndex.value ?: MainViewModel.INVALID_INDEX,
      viewModel.disableIndex.value ?: MainViewModel.INVALID_INDEX,
      REQUEST_KEY_CUSTOM
    )
    dialog.show(supportFragmentManager, TAG_CUSTOM)
  }

  private fun showNormalDialog() {
    val dialog = NormalSingleChoiceItemDialogFragment.newInstance(
      getString(R.string.normal_dialog_text),
      dialogValues,
      viewModel.disableIndex.value ?: MainViewModel.INVALID_INDEX,
      REQUEST_KEY_NORMAL
    )
    dialog.show(supportFragmentManager, TAG_NORMAL)
  }

  companion object {
    private const val REQUEST_KEY_NORMAL = "request_key_normal"
    private const val REQUEST_KEY_CUSTOM = "request_key_custom"
    private const val TAG_NORMAL = "NormalSingleChoiceItemDialogFragment"
    private const val TAG_CUSTOM = "CustomSingleChoiceItemDialogFragment"
  }
}