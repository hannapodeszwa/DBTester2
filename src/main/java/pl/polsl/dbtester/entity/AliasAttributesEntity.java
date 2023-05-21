package pl.polsl.dbtester.entity;

//import jakarta.persistence.*;
import javax.persistence.*;

@Entity
@Table(name = "alias_attributes", schema = "imdb", catalog = "")
@IdClass(AliasAttributesEntityPK.class)
public class AliasAttributesEntity {

    @Id
    @Column(name = "title_id")
    private String titleId;

    @Id
    @Column(name = "ordering")
    private int ordering;
    @Basic
    @Column(name = "attribute")
    private String attribute;
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

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AliasAttributesEntity that = (AliasAttributesEntity) o;

        if (ordering != that.ordering) return false;
        if (titleId != null ? !titleId.equals(that.titleId) : that.titleId != null) return false;
        if (attribute != null ? !attribute.equals(that.attribute) : that.attribute != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = titleId != null ? titleId.hashCode() : 0;
        result = 31 * result + ordering;
        result = 31 * result + (attribute != null ? attribute.hashCode() : 0);
        return result;
    }

    public TitlesEntity getTitlesByTitleId() {
        return titlesByTitleId;
    }

    public void setTitlesByTitleId(TitlesEntity titlesByTitleId) {
        this.titlesByTitleId = titlesByTitleId;
    }
}
