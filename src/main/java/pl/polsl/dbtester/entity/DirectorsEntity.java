package pl.polsl.dbtester.entity;

//import jakarta.persistence.*;
import javax.persistence.*;

@Entity
@Table(name = "directors", schema = "imdb", catalog = "")
@IdClass(DirectorsEntityPK.class)
public class DirectorsEntity {
    @Id
    @Column(name = "title_id")
    private String titleId;
    @Id
    @Column(name = "name_id")
    private String nameId;
    @ManyToOne
    @JoinColumn(name = "title_id", referencedColumnName = "title_id", nullable = false, insertable = false, updatable = false)
    private TitlesEntity titlesByTitleId;
    @ManyToOne
    @JoinColumn(name = "name_id", referencedColumnName = "name_id", nullable = false, insertable = false, updatable = false)
    private NamesEntity namesByNameId;

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirectorsEntity that = (DirectorsEntity) o;

        if (titleId != null ? !titleId.equals(that.titleId) : that.titleId != null) return false;
        if (nameId != null ? !nameId.equals(that.nameId) : that.nameId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = titleId != null ? titleId.hashCode() : 0;
        result = 31 * result + (nameId != null ? nameId.hashCode() : 0);
        return result;
    }

    public TitlesEntity getTitlesByTitleId() {
        return titlesByTitleId;
    }

    public void setTitlesByTitleId(TitlesEntity titlesByTitleId) {
        this.titlesByTitleId = titlesByTitleId;
    }

    public NamesEntity getNamesByNameId() {
        return namesByNameId;
    }

    public void setNamesByNameId(NamesEntity namesByNameId) {
        this.namesByNameId = namesByNameId;
    }
}
