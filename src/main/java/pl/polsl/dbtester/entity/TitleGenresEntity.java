package pl.polsl.dbtester.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "title_genres", schema = "imdb", catalog = "")
@IdClass(TitleGenresEntityPK.class)
public class TitleGenresEntity {
    @Id
    @Column(name = "title_id")
    private String titleId;
    @Id
    @Column(name = "genre")
    private String genre;
    @ManyToOne
    @JoinColumn(name = "title_id", referencedColumnName = "title_id", nullable = false, insertable = false, updatable = false)
    private TitlesEntity titlesByTitleId;

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

        TitleGenresEntity that = (TitleGenresEntity) o;

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

    public TitlesEntity getTitlesByTitleId() {
        return titlesByTitleId;
    }

    public void setTitlesByTitleId(TitlesEntity titlesByTitleId) {
        this.titlesByTitleId = titlesByTitleId;
    }
}
