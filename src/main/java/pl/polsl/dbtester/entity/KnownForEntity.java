package pl.polsl.dbtester.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "known_for", schema = "imdb", catalog = "")
@IdClass(KnownForEntityPK.class)
public class KnownForEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "name_id")
    private String nameId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "title_id")
    private String titleId;
    @ManyToOne
    @JoinColumn(name = "name_id", referencedColumnName = "name_id", nullable = false, insertable = false, updatable = false)
    private NamesEntity namesByNameId;
    @ManyToOne
    @JoinColumn(name = "title_id", referencedColumnName = "title_id", nullable = false, insertable = false, updatable = false)
    private TitlesEntity titlesByTitleId;

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KnownForEntity that = (KnownForEntity) o;

        if (nameId != null ? !nameId.equals(that.nameId) : that.nameId != null) return false;
        if (titleId != null ? !titleId.equals(that.titleId) : that.titleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nameId != null ? nameId.hashCode() : 0;
        result = 31 * result + (titleId != null ? titleId.hashCode() : 0);
        return result;
    }

    public NamesEntity getNamesByNameId() {
        return namesByNameId;
    }

    public void setNamesByNameId(NamesEntity namesByNameId) {
        this.namesByNameId = namesByNameId;
    }

    public TitlesEntity getTitlesByTitleId() {
        return titlesByTitleId;
    }

    public void setTitlesByTitleId(TitlesEntity titlesByTitleId) {
        this.titlesByTitleId = titlesByTitleId;
    }
}
