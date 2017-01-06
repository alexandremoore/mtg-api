package ca.alexandremoore.mtg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ruling {

    @JsonProperty(required = true)
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @JsonProperty(required = true)
    private String text;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, text);
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Ruling other = (Ruling) obj;
        return Objects.equals(date, other.date) && Objects.equals(text, other.text);
    }

}