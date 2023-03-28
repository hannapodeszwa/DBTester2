package pl.polsl.dbtester.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class WritersEntityPK implements Serializable {
    @Column(name = "title_id")
    @Id
    private String titleId;
    @Column(name = "name_id")
    @Id
    private String nameId;

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

        WritersEntityPK that = (WritersEntityPK) o;

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
}
