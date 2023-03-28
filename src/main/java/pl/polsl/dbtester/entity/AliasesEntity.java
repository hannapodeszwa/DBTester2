package pl.polsl.dbtester.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "aliases", schema = "imdb", catalog = "")
@IdClass(AliasesEntityPK.class)
public class AliasesEntity {

    @Id
    @Column(name = "title_id")
    private String titleId;
    @Id
    @Column(name = "ordering")
    private int ordering;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "region")
    private String region;
    @Basic
    @Column(name = "language")
    private String language;
    @Basic
    @Column(name = "is_original_title")
    private Byte isOriginalTitle;
    @ManyToOne
    @JoinColumn(name = "title_id", referencedColumnName = "title_id", nullable = false, insertable = false, updatable = false)
    private TitlesEntity titlesByTitleId;

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Byte getIsOriginalTitle() {
        return isOriginalTitle;
    }

    public void setIsOriginalTitle(Byte isOriginalTitle) {
        this.isOriginalTitle = isOriginalTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AliasesEntity that = (AliasesEntity) o;

        if (ordering != that.ordering) return false;
        if (titleId != null ? !titleId.equals(that.titleId) : that.titleId != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (isOriginalTitle != null ? !isOriginalTitle.equals(that.isOriginalTitle) : that.isOriginalTitle != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = titleId != null ? titleId.hashCode() : 0;
        result = 31 * result + ordering;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (isOriginalTitle != null ? isOriginalTitle.hashCode() : 0);
        return result;
    }

    public TitlesEntity getTitlesByTitleId() {
        return titlesByTitleId;
    }

    public void setTitlesByTitleId(TitlesEntity titlesByTitleId) {
        this.titlesByTitleId = titlesByTitleId;
    }
}
