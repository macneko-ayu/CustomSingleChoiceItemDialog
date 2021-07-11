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
  private val disableIndex = 2

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding.normalDialogButton.setOnClickListener {
      val dialog = NormalSingleChoiceItemDialogFragment.newInstance(
        getString(R.string.normal_dialog_text),
        resources.getStringArray(R.array.dialog_values).toList(),
        0,
        REQUEST_KEY_NORMAL)
      dialog.show(supportFragmentManager, TAG_NORMAL)
    }
    binding.customDialogButton.setOnClickListener {
      val dialog = CustomSingleChoiceItemDialogFragment.newInstance(
        getString(R.string.custom_dialog_text),
        resources.getStringArray(R.array.dialog_values).toList(),
        0,
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