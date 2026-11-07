# SAKE compass — ER図（データベース設計）

銘柄データをSAKEテーブルに保存します。現在のCRUD機能では、このテーブルの登録・表示・削除を実装しています。

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

## テーブル項目

| 項目 | 型 | 制約・用途 |
|---|---|---|
| id | BIGINT | 主キー、自動採番 |
| name | VARCHAR(100) | 銘柄名、必須 |
| brewery | VARCHAR(100) | 蔵元、必須 |
| prefecture | VARCHAR(40) | 都道府県、必須 |
| sake_type | VARCHAR(50) | 酒の種類、必須 |
| flavor | VARCHAR(500) | 味の説明、必須 |
| taste_group | VARCHAR(40) | 味わいカテゴリ、必須 |
| aroma_level | INTEGER | 香りの強さ、1〜5 |
| body_level | INTEGER | 飲みごたえ、1〜5 |
| pairings | VARCHAR(200) | 合わせる料理 |
| image_url | VARCHAR(500) | ボトル画像URL |
| product_url | VARCHAR(500) | 蔵元・商品情報URL |

## テーブル間の関係

現在はSAKEテーブル1つで構成するため、テーブル間のリレーションはありません。
