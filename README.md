# training-head

# ソースコードの実行方法（ターミナルの実行コマンド）
## gitclone & コンパイル
git clone https://github.com/xbridges/training-head.git
mvn install

## 実行
```
java -jar .\target\CommandEmulater-1.0.jar <filepath1> <filepath2> ... [-c|--bytes=n or [-n|--lines=n]] [-v|-q]
```

### 必須引数
```
<filepath>は任意のファイル引数数分設定が可能です。
ワイルドカードは未対応です。
```
### オプション
```
-c|--bytes: 文字数指定モード(default: 5文字)
-n|--lines: 行数指定モード(default: 5行)
-v: ファイル名強制表示モード(default)
-q: ファイル名非表示モード
```

# 工夫した点、アピールポイント等

- headの挙動をなるべく同じになるように再現いたしました。
- 内部挙動としては、読込専用モードでの読込はしておらず、単純に書込みメソッドを利用しない形で実現しています。
- flagの挙動も-cおよび-nでの無効な値を設定された場合、できる限り表示できるようデフォルト値を使って表示しています。
- 小さなプロジェクトとしてフォルダ構成を現在弊社で作成するプロジェクトフォルダに合わせてあります。
- データの扱いは基本的にrepository経由を意識したつくりで作っています。
