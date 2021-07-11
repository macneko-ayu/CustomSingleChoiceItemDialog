package com.macneko.customsinglechoiceitemdialog.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.view.get
import com.macneko.customsinglechoiceitemdialog.R
import com.macneko.customsinglechoiceitemdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
  private val binding: ActivityMainBinding by lazy {
    ActivityMainBinding.bind(findViewById<ViewGroup>(android.R.id.content)[0])
  }
  private var disableIndex = -1
  private var selectedIndex = -1
  private var selectedItem = "Not selected item"
  private val dialogValues: List<String>
    get() = resources.getStringArray(R.array.dialog_values).toList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    supportFragmentManager.setFragmentResultListener(REQUEST_KEY_NORMAL, this) { _, bundle ->
      disableIndex = bundle.getInt(NormalSingleChoiceItemDialogFragment.RESULT_KEY, -1)
      binding.disabledLabel.text = getString(R.string.disabled_label, disableIndex)
    }
    supportFragmentManager.setFragmentResultListener(REQUEST_KEY_CUSTOM, this) { _, bundle ->
      selectedIndex = bundle.getInt(CustomSingleChoiceItemDialogFragment.RESULT_KEY, -1)
      binding.resultText.text = if (selectedIndex > -1) {
        dialogValues[selectedIndex]
      } else {
        "Not selected item"
      }
    }

    binding.resultText.text = selectedItem
    binding.disabledLabel.text = getString(R.string.disabled_label, disableIndex)
    binding.normalDialogButton.setOnClickListener {
      val dialog = NormalSingleChoiceItemDialogFragment.newInstance(
        getString(R.string.normal_dialog_text),
        dialogValues,
        disableIndex,
        REQUEST_KEY_NORMAL)
      dialog.show(supportFragmentManager, TAG_NORMAL)
    }
    binding.customDialogButton.setOnClickListener {
      val dialog = CustomSingleChoiceItemDialogFragment.newInstance(
        getString(R.string.custom_dialog_text),
        dialogValues,
        selectedIndex,
        disableIndex,
        REQUEST_KEY_CUSTOM)
      dialog.show(supportFragmentManager, TAG_CUSTOM)
    }
  }

  companion object {
    private const val REQUEST_KEY_NORMAL = "request_key_normal"
    private const val REQUEST_KEY_CUSTOM = "request_key_custom"
    private const val TAG_NORMAL = "NormalSingleChoiceItemDialogFragment"
    private const val TAG_CUSTOM = "CustomSingleChoiceItemDialogFragment"
  }
}