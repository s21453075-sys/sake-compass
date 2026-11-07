# SAKE compass — 使用技術（技術スタック）

| カテゴリ | 技術 | 用途 |
|---|---|---|
| フロントエンド | HTML / CSS | 画面構造、レスポンシブ表示、銘柄一覧と登録フォーム |
| フロントエンド | JavaScript（Vanilla JS） | 味わいフィルター、API呼び出し、一覧更新、登録・削除 |
| バックエンド | Java 11 / Spring Boot 2.7.18 | REST APIとアプリケーションの起動 |
| API | Spring Web | HTTPリクエストとJSONレスポンス |
| データアクセス | Spring Data JPA | JavaオブジェクトとDB間の保存・取得・削除 |
| データベース | H2 Database | 銘柄情報をファイル形式で保存 |
| 入力検証 | Bean Validation | 必須項目、文字数、数値範囲の検証 |
| ビルド | Apache Maven | 依存関係の解決と実行可能JARの作成 |

## API一覧

- GET /api/sakes — 銘柄一覧
- GET /api/sakes/{id} — 銘柄の詳細
- POST /api/sakes — 銘柄登録
- DELETE /api/sakes/{id} — 銘柄削除
