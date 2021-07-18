# CustomSingleChoiceItemDialog
`SingleChoiceItem` をカスタマイズして、リスト内の特定の要素を disable にできるようにした `DialogFragment` のサンプルです 

## デモ

https://user-images.githubusercontent.com/5406126/125948053-4103f011-e149-4220-a952-cff3791c7a78.mov

## 想定しているユースケース
A と B という複数の設定がある設定画面で、A 設定で選択した要素に応じて、B 設定で選択できなくなる要素があるケースを想定しています
## 画面の説明
<img src="https://user-images.githubusercontent.com/5406126/126057333-844097ae-8c92-41fc-a3e7-3a120882efd2.png" width="320" />

合番 | 説明
--- | ---  
1 | `4` をタップして表示されたダイアログで選択した要素を表示します  
2 | disable にする要素を選択するダイアログを表示します  
3 | `2` で disable にした要素の position を表示します  
4 | `2` で選択した要素を disable にしたダイアログを表示します   

## 使い方
1. `CustomSingleChoiceItemDialogFragment` と `CustomAdapter` をプロジェクトに追加する
2. `Activity` などのダイアログを表示するクラスにダイアログを表示するコードを書く
```kotlin
val dialog = CustomSingleChoiceItemDialogFragment.newInstance(
    "title", // ダイアログのタイトル
    ["First", "Second", "Third"], // リストに表示する値の配列
    0, // リストで選択状態にする index
    1, // リストで disable 状態にする index。`-1` で disable なし
    "REQUEST_KEY_CUSTOM" // setFragmentResultListener で選択結果を受け取るときの Key
)
dialog.show(supportFragmentManager, "CustomSingleChoiceItemDialogFragment")
```
3. 2 のクラスの onCreate にダイアログの選択結果を受け取るコードを書く
```kotlin
supportFragmentManager.setFragmentResultListener("REQUEST_KEY_CUSTOM", this) { _, bundle ->
      Log.d("sample", bundle.getInt(CustomSingleChoiceItemDialogFragment.RESULT_KEY, -1)) // bundle に選択した index が入っているので、取り出してログに出力する
}
```

## Author

[@macneko_ayu](https://twitter.com/macneko_ayu)

## Licence

This software is released under the MIT License, see LICENSE.txt.
