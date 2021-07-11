package com.macneko.customsinglechoiceitemdialog.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.macneko.customsinglechoiceitemdialog.adapter.CustomAdapter

class CustomSingleChoiceItemDialogFragment : DialogFragment() {
  private lateinit var title: String
  private lateinit var entries: List<String>
  private var entryIndex = 0
  private var disableIndex = -1
  private lateinit var adapter: CustomAdapter
  private lateinit var requestKey: String

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    arguments?.run {
      title = getString(PARAM_TITLE) ?: throw IllegalArgumentException("arg is null")
      entries = getStringArrayList(PARAM_ENTRIES) ?: throw IllegalArgumentException(
        "arg is null"
      )
      entryIndex = getInt(PARAM_ENTRY_INDEX)
      disableIndex = getInt(PARAM_DISABLE_INDEX, -1)
      requestKey = getString(PARAM_REQUEST_KEY) ?: throw IllegalArgumentException("arg is null")
    }

    adapter = CustomAdapter(entries, disableIndex)
  }

  override fun onCreateDialog(savedInstanceState: Bundle?) =
    AlertDialog.Builder(context).apply {
      setTitle(title)
      setSingleChoiceItems(adapter, entryIndex) { _, selectedIndex ->
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
    private const val PARAM_DISABLE_INDEX = "param_disable_index"
    private const val PARAM_REQUEST_KEY = "PARAM_REQUEST_KEY"
    const val RESULT_KEY = "result_key"

    fun newInstance(
      title: String,
      entries: List<String>,
      entryIndex: Int,
      disableIndex: Int = -1,
      requestKey: String
    ) =
      CustomSingleChoiceItemDialogFragment().apply {
        arguments = Bundle().apply {
          putString(PARAM_TITLE, title)
          putStringArrayList(PARAM_ENTRIES, ArrayList(entries))
          putInt(PARAM_ENTRY_INDEX, entryIndex)
          putInt(PARAM_DISABLE_INDEX, disableIndex)
          putString(PARAM_REQUEST_KEY, requestKey)
        }
      }
  }
}