package pl.polsl.dbtester.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class KnownForEntityPK implements Serializable {
    @Column(name = "name_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nameId;
    @Column(name = "title_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String titleId;

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

        KnownForEntityPK that = (KnownForEntityPK) o;

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
}
