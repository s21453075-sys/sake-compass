package jp.sakecompass.sake;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "sakes")
public class Sake {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max=100) @Column(nullable=false,length=100)
    private String name;
    @NotBlank @Size(max=100) @Column(nullable=false,length=100)
    private String brewery;
    @NotBlank @Size(max=40) @Column(nullable=false,length=40)
    private String prefecture;
    @NotBlank @Size(max=50) @Column(nullable=false,length=50)
    private String sakeType;
    @NotBlank @Size(max=500) @Column(nullable=false,length=500)
    private String flavor;
    @NotBlank @Size(max=40) @Column(nullable=false,length=40)
    private String tasteGroup;
    @Min(1) @Max(5) private int aromaLevel;
    @Min(1) @Max(5) private int bodyLevel;
    @Size(max=200) @Column(length=200) private String pairings;
    @Size(max=500) @Column(length=500) private String imageUrl;
    @Size(max=500) @Column(length=500) private String productUrl;

    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getBrewery(){return brewery;} public void setBrewery(String v){brewery=v;}
    public String getPrefecture(){return prefecture;} public void setPrefecture(String v){prefecture=v;}
    public String getSakeType(){return sakeType;} public void setSakeType(String v){sakeType=v;}
    public String getFlavor(){return flavor;} public void setFlavor(String v){flavor=v;}
    public String getTasteGroup(){return tasteGroup;} public void setTasteGroup(String v){tasteGroup=v;}
    public int getAromaLevel(){return aromaLevel;} public void setAromaLevel(int v){aromaLevel=v;}
    public int getBodyLevel(){return bodyLevel;} public void setBodyLevel(int v){bodyLevel=v;}
    public String getPairings(){return pairings;} public void setPairings(String v){pairings=v;}
    public String getImageUrl(){return imageUrl;} public void setImageUrl(String v){imageUrl=v;}
    public String getProductUrl(){return productUrl;} public void setProductUrl(String v){productUrl=v;}
}