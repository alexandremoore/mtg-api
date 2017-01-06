package ca.alexandremoore.mtg.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.ResourceSupport;

import java.util.Date;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MtgSetResource extends ResourceSupport {

    private String name;

    private String code;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date releaseDate;

    private String border;

    private String type;

    private String block;

//    private java.util.MtgSet<MtgCard> cards;

    // Extra for card in set
    private String rarity;

    private String artist;

    private Integer multiverseid;

    private String imageName;

    private String flavor;

    private String number;

    private String layout;

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Integer getMultiverseid() {
        return multiverseid;
    }

    public void setMultiverseid(Integer multiverseid) {
        this.multiverseid = multiverseid;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

//    public java.util.MtgSet<MtgCard> getMtgCards() {
//        return cards;
//    }
//
//    public void setMtgCards(java.util.MtgSet<MtgCard> cards) {
//        this.cards = cards;
//    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        MtgSetResource other = (MtgSetResource) obj;
        return Objects.equals(name, other.name);
    }

}
