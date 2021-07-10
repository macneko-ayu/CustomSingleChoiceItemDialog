package com.macneko.customsinglechoiceitemdialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.view.get
import com.macneko.customsinglechoiceitemdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
  private val binding: ActivityMainBinding by lazy {
    ActivityMainBinding.bind(findViewById<ViewGroup>(android.R.id.content)[0])
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }
}