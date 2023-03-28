package pl.polsl.dbtester.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class NameWorkedAsEntityPK implements Serializable {
    @Column(name = "name_id")
    @Id
    private String nameId;
    @Column(name = "profession")
    @Id
    private String profession;

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

        NameWorkedAsEntityPK that = (NameWorkedAsEntityPK) o;

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
}
