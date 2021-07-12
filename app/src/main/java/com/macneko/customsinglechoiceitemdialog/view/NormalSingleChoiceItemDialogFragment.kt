package com.macneko.customsinglechoiceitemdialog.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class NormalSingleChoiceItemDialogFragment : DialogFragment() {
  private lateinit var title: String
  private lateinit var entries: List<String>
  private var entryIndex = 0
  private lateinit var requestKey: String

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    arguments?.run {
      title = getString(PARAM_TITLE) ?: throw IllegalArgumentException("arg is null")
      entries = getStringArrayList(PARAM_ENTRIES) ?: throw IllegalArgumentException(
        "arg is null"
      )
      entryIndex = getInt(PARAM_ENTRY_INDEX)
      requestKey = getString(PARAM_REQUEST_KEY) ?: throw IllegalArgumentException("arg is null")
    }
  }

  override fun onCreateDialog(savedInstanceState: Bundle?) =
    AlertDialog.Builder(context).apply {
      setTitle(title)
      setSingleChoiceItems(entries.toTypedArray(), entryIndex) { _, selectedIndex ->
        val bundle = Bundle().apply {
          putInt(RESULT_KEY, selectedIndex)
        }
        parentFragmentManager.setFragmentResult(requestKey, bundle)
        dismiss()
      }
      setNegativeButton(android.R.string.cancel) { _, _ ->
        dismiss()
      }
    }.create() ?: super.onCreateDialog(savedInstanceState)

  companion object {
    private const val PARAM_TITLE = "param_title"
    private const val PARAM_ENTRIES = "param_entries"
    private const val PARAM_ENTRY_INDEX = "param_entry_index"
    private const val PARAM_REQUEST_KEY = "PARAM_REQUEST_KEY"
    const val RESULT_KEY = "result_key"

    fun newInstance(
      title: String,
      entries: List<String>,
      entryIndex: Int,
      requestKey: String
    ) =
      NormalSingleChoiceItemDialogFragment().apply {
        arguments = Bundle().apply {
          putString(PARAM_TITLE, title)
          putStringArrayList(PARAM_ENTRIES, ArrayList(entries))
          putInt(PARAM_ENTRY_INDEX, entryIndex)
          putString(PARAM_REQUEST_KEY, requestKey)
        }
      }
  }
}