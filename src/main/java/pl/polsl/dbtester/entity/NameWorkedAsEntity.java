package pl.polsl.dbtester.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "name_worked_as", schema = "imdb", catalog = "")
@IdClass(NameWorkedAsEntityPK.class)
public class NameWorkedAsEntity {
    @Id
    @Column(name = "name_id")
    private String nameId;
    @Id
    @Column(name = "profession")
    private String profession;
    @ManyToOne
    @JoinColumn(name = "name_id", referencedColumnName = "name_id", nullable = false, insertable = false, updatable = false)
    private NamesEntity namesByNameId;

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NameWorkedAsEntity that = (NameWorkedAsEntity) o;

        if (nameId != null ? !nameId.equals(that.nameId) : that.nameId != null) return false;
        if (profession != null ? !profession.equals(that.profession) : that.profession != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nameId != null ? nameId.hashCode() : 0;
        result = 31 * result + (profession != null ? profession.hashCode() : 0);
        return result;
    }

    public NamesEntity getNamesByNameId() {
        return namesByNameId;
    }

    public void setNamesByNameId(NamesEntity namesByNameId) {
        this.namesByNameId = namesByNameId;
    }
}
