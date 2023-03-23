package pl.polsl.dbtester.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class TitleGenresEntityPK implements Serializable {
    @Column(name = "title_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String titleId;
    @Column(name = "genre")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String genre;

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TitleGenresEntityPK that = (TitleGenresEntityPK) o;

        if (titleId != null ? !titleId.equals(that.titleId) : that.titleId != null) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = titleId != null ? titleId.hashCode() : 0;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }
}
