package ca.alexandremoore.mtg.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MtgCardResource extends ResourceSupport {

    private String cardId;

    private String layout;

    private String name;

    private String manaCost;

    private double cmc;

    private Set<String> colors;

    private Set<String> subtypes;

    private Set<String> types;

    private String rarity;

    private String text;

    private String flavor;

    private String artist;

    private String number;

    private String power;

    private String toughness;

    private Integer multiverseid;

    private String imageName;

    private Set<String> printings;

    private List<MtgSetResource> editions;

    public List<MtgSetResource> getEditions() {
        return editions;
    }

    public void setEditions(List<MtgSetResource> editions) {
        this.editions = editions;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
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

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public double getCmc() {
        return cmc;
    }

    public void setCmc(double cmc) {
        this.cmc = cmc;
    }

    public Set<String> getColors() {
        return colors;
    }

    public void setColors(Set<String> colors) {
        this.colors = colors;
    }

    public Set<String> getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(Set<String> subtypes) {
        this.subtypes = subtypes;
    }

    public Set<String> getTypes() {
        return types;
    }

    public void setTypes(Set<String> types) {
        this.types = types;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
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

    public Set<String> getPrintings() {
        return printings;
    }

    public void setPrintings(Set<String> printings) {
        this.printings = printings;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cardId);
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        MtgCardResource other = (MtgCardResource) obj;
        return Objects.equals(cardId, other.cardId);
    }

}
