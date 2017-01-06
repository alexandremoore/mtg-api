package ca.alexandremoore.mtg.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MtgSet {

    @JsonProperty(required = true)
    private String name;

    @JsonProperty(required = true)
    private String code;

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @JsonProperty(required = true)
    private String border;

    @JsonProperty(required = true)
    private String type;

    private String block;

    private ArrayList<MtgCard> cards;

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

    public ArrayList<MtgCard> getCards() {
        return cards;
    }

    public void setCards(ArrayList<MtgCard> cards) {
        this.cards = cards;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        MtgSet other = (MtgSet) obj;
        return Objects.equals(name, other.name);
    }

}
