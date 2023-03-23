package pl.polsl.dbtester.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class AliasesEntityPK implements Serializable {
    @Column(name = "title_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String titleId;
    @Column(name = "ordering")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ordering;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AliasesEntityPK that = (AliasesEntityPK) o;

        if (ordering != that.ordering) return false;
        if (titleId != null ? !titleId.equals(that.titleId) : that.titleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = titleId != null ? titleId.hashCode() : 0;
        result = 31 * result + ordering;
        return result;
    }
}
