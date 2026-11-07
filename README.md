# SAKE compass

日本酒の好みが分からず、居酒屋で注文に迷う人向けのガイドです。味わい別に銘柄を探し、銘柄を登録・一覧表示・削除できます。

## 使用技術

| 分類 | 技術 |
|---|---|
| フロントエンド | HTML / CSS / JavaScript（Vanilla JS） |
| バックエンド | Java 11 / Spring Boot 2.7.18 / Spring Web |
| データアクセス | Spring Data JPA |
| データベース | PostgreSQL（本番環境） / H2 Database（ローカル開発） |
| 入力検証 | Bean Validation |
| ビルド | Apache Maven |

画面のJavaScriptがfetchでSpring Boot REST APIを呼び、APIがJPAを通じてデータベースを操作します。公開環境ではRender PostgreSQLの `DATABASE_URL` を読み込み、ローカル環境ではH2を使用します。

## 機能

- 味わいカテゴリで銘柄を絞り込む
- DBから銘柄を読み込んで一覧表示
- 銘柄情報の登録と削除
- 本番環境ではPostgreSQL、ローカル開発ではH2に保存
- 初回起動時にサンプル10銘柄を登録

登録項目: 銘柄名、蔵元、産地、種類、味の説明、味わいカテゴリ、香り、飲みごたえ、料理、画像URL、商品情報URL。

## API

| Method | Path | 用途 |
|---|---|---|
| GET | /api/sakes | 銘柄一覧 |
| GET | /api/sakes/{id} | 銘柄1件 |
| POST | /api/sakes | 銘柄を登録 |
| DELETE | /api/sakes/{id} | 銘柄を削除 |

POST JSONの例:

~~~json
{
  "name": "試飲メモ 純米吟醸",
  "brewery": "サンプル酒造",
  "prefecture": "長野県",
  "sakeType": "純米吟醸",
  "flavor": "穏やかな香りと米のうまみ",
  "tasteGroup": "米のうまみ・コク",
  "aromaLevel": 3,
  "bodyLevel": 4,
  "pairings": "焼き魚",
  "imageUrl": "",
  "productUrl": ""
}
~~~

## ER図

~~~mermaid
erDiagram
    SAKE {
        bigint id PK "銘柄ID"
        varchar name "銘柄名"
        varchar brewery "蔵元"
        varchar prefecture "都道府県"
        varchar sake_type "酒の種類"
        varchar flavor "味わいの説明"
        varchar taste_group "味わいカテゴリ"
        int aroma_level "香りの強さ 1-5"
        int body_level "飲みごたえ 1-5"
        varchar pairings "おすすめ料理"
        varchar image_url "ボトル画像URL"
        varchar product_url "商品情報URL"
    }
~~~

現状のCRUD対象はSAKEテーブルです。味の数値1〜5は比較の目安です。

## ローカル起動

必要なもの: JDK 11以上。初回はMaven本体と依存ライブラリをインターネットから取得します。

~~~powershell
.\mvnw.cmd spring-boot:run
~~~

起動後に http://localhost:8080/ を開きます。JSON一覧は http://localhost:8080/api/sakes です。H2 Consoleは http://localhost:8080/h2-console です。ローカルのJDBC URLは jdbc:h2:file:./data/sake_compass、ユーザー名は sa、パスワードは空です。

ビルド:

~~~powershell
.\mvnw.cmd clean package
java -jar target/sake-compass-1.0.0.jar
~~~

## 無料枠での公開

ルートの `render.yaml` はRenderの無料Webサービスと無料PostgreSQLを作り、DB接続も自動設定するBlueprintです。[RenderのBlueprint](https://render.com/deploy?repo=https://github.com/s21453075-sys/sake-compass)を開き、`sake-compass` リポジトリを選択してデプロイします。完了後に表示されるRender URLでアプリを開けます。

無料Webサービスは15分アクセスがないと休止し、次のアクセス時に起動します。無料PostgreSQLは1GBで、作成から30日後に期限切れになります。提出・デモ用の無料公開として利用できますが、DBを継続利用する場合は期限前に有料プランへの変更が必要です。[Renderの無料枠と制限](https://render.com/docs/free)

## 画面証跡

デプロイ後に公開URLをブラウザーで開き、画面全体のスクリーンショットを取得して提出資料に添付してください。

## 銘柄・画像について

サンプル10銘柄を初期データとして登録しています。味の説明は各蔵元・商品案内を参考にした目安です。画像は外部サイトから読み込みます。お酒は20歳になってから。飲酒運転は法律で禁じられています。

