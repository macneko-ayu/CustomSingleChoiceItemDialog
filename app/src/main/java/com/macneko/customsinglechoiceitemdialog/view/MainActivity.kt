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
  }

  companion object {
    private const val REQUEST_KEY_NORMAL = "request_key_normal"
    private const val TAG_NORMAL = "NormalSingleChoiceItemDialogFragment"
  }
}