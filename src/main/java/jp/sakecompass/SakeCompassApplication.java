package jp.sakecompass;

import jp.sakecompass.sake.Sake;
import jp.sakecompass.sake.SakeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Arrays;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class SakeCompassApplication {
    public static void main(String[] args) {
        new java.io.File("data").mkdirs();
        configureDatabaseFromEnvironment();
        SpringApplication.run(SakeCompassApplication.class, args);
    }

    private static void configureDatabaseFromEnvironment() {
        String databaseUrl = System.getenv("DATABASE_URL");
        if (databaseUrl == null || databaseUrl.trim().isEmpty()) return;

        URI uri = URI.create(databaseUrl.replaceFirst("^postgres(ql)?://", "http://"));
        String[] credentials = uri.getUserInfo() == null ? new String[0] : uri.getUserInfo().split(":", 2);
        if (uri.getHost() == null || credentials.length != 2) {
            throw new IllegalArgumentException("DATABASE_URL の形式を確認してください。");
        }

        String jdbcUrl = "jdbc:postgresql://" + uri.getHost()
            + (uri.getPort() > 0 ? ":" + uri.getPort() : "") + uri.getRawPath();
        if (uri.getRawQuery() != null) jdbcUrl += "?" + uri.getRawQuery();

        System.setProperty("spring.datasource.url", jdbcUrl);
        System.setProperty("spring.datasource.username", decodeUriPart(credentials[0]));
        System.setProperty("spring.datasource.password", decodeUriPart(credentials[1]));
        System.setProperty("spring.datasource.driver-class-name", "org.postgresql.Driver");
    }

    private static String decodeUriPart(String value) {
        return URLDecoder.decode(value.replace("+", "%2B"), StandardCharsets.UTF_8);
    }

    @Bean
    CommandLineRunner seedCatalog(SakeRepository repository) {
        return args -> {
            if (repository.count() != 0) return;
            repository.saveAll(Arrays.asList(
                sake("獺祭 純米大吟醸45","旭酒造","山口県","純米大吟醸","華やかな香りと米由来の繊細な甘み。","華やか・果実香",5,2,"刺身・寿司,チーズ","https://www.dassaistore.com/uploads/ZIMWelwKAxfEljf4pAvP92TIBvlZhtRPeIxJbqvg-small.jpg","https://dassai.com/product/main/45.html"),
                sake("久保田 千寿 吟醸","朝日酒造","新潟県","吟醸","穏やかな香りとすっきりした味わい。食事に合わせやすい定番。","すっきり・辛口",2,2,"刺身・寿司,焼き魚","https://www.asahi-shuzo.co.jp/_src/94087385/bottle_kubota_senjyu.png","https://www.asahi-shuzo.co.jp/kubota/"),
                sake("八海山 特別本醸造","八海醸造","新潟県","特別本醸造","やわらかな口当たりと淡麗な味わい。冷酒でも燗でも。","すっきり・辛口",2,2,"刺身・寿司,焼き魚","https://www.hakkaisan.co.jp/wp-content/uploads/2024/05/tokubetu-honjyozou_720ml_-347x800.jpg","https://www.hakkaisan.co.jp/sake/honjyozo/"),
                sake("出羽桜 桜花吟醸酒","出羽桜酒造","山形県","吟醸","吟醸香を楽しむ定番。少し冷やして香りを楽しみたい一本。","華やか・果実香",5,2,"刺身・寿司,まずは一杯","https://www.dewazakura.co.jp/item/img/okanama-main.png","https://www.dewazakura.co.jp/item/cat03/oka-nama.html"),
                sake("純米辛口 浦霞","佐浦","宮城県","純米","心地よい香りと米のうまみ。後味はキレよく飲み飽きしにくい。","辛口・米のうまみ",2,3,"刺身・寿司,焼き魚","https://www.urakasumi.com/wp-content/uploads/2024/02/junmai_kara720.jpg","https://www.urakasumi.com/items/junmaikarakuchi/"),
                sake("真澄 辛口生一本","宮坂醸造","長野県","純米吟醸","やわらかさと辛口のバランス。りんごを思わせる香りと爽やかな後口。","辛口・果実香",3,3,"刺身・寿司,焼き魚","https://www.masumi.co.jp/cn/wp/wp-content/uploads/2024/09/mainP-018_5A_5874.jpg","https://www.masumi.co.jp/product/"),
                sake("而今 特別純米","木屋正酒造","三重県","特別純米","みずみずしい果実香と透明感のある米のうまみ。","華やか・米のうまみ",4,3,"刺身・寿司,チーズ","https://cdn.shopify.com/s/files/1/0575/1354/1797/files/kiyasho_yt1.webp","https://imadeya.co.jp/pages/export_jikon"),
                sake("田酒 特別純米酒","西田酒造店","青森県","特別純米","米の味わいを感じる、コクとキレのバランス型。","辛口・米のうまみ",2,4,"焼き魚,刺身・寿司","https://kichiriki.com/wp-content/uploads/2024/09/amt032-a.jpg","https://www.granvia-kyoto.co.jp/datas/files/2025/12/19/9aef1f912d52af803369789b59a0e03c710569c2.pdf"),
                sake("黒龍 吟醸 いっちょらい","黒龍酒造","福井県","吟醸","吟醸らしい香りと、軽快ですっきりした飲み口。","華やか・すっきり",4,2,"刺身・寿司,焼き魚","https://shop.r10s.jp/hyousuke/cabinet/kokuryu/imgrc0117204616.jpg","https://www.kokuryu.co.jp/220th_anniversary/product"),
                sake("日本盛 特撰","日本盛","兵庫県","本醸造","ふくよかな香りとまろやかな味わい。ぬる燗にも。","米のうまみ・コク",2,4,"焼き魚,チーズ","https://www.nihonsakari.co.jp/images/products/b17518b0dcba143c5296b6d81714b7af.jpg","https://www.nihonsakari.co.jp/products/p/1")
            ));
        };
    }

    private Sake sake(String name,String brewery,String prefecture,String type,String flavor,String tasteGroup,int aroma,int body,String pairings,String imageUrl,String productUrl) {
        Sake sake = new Sake();
        sake.setName(name); sake.setBrewery(brewery); sake.setPrefecture(prefecture);
        sake.setSakeType(type); sake.setFlavor(flavor); sake.setTasteGroup(tasteGroup);
        sake.setAromaLevel(aroma); sake.setBodyLevel(body); sake.setPairings(pairings);
        sake.setImageUrl(imageUrl); sake.setProductUrl(productUrl);
        return sake;
    }
}

