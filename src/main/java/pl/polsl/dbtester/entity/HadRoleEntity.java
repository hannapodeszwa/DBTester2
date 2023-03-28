package pl.polsl.dbtester.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "had_role", schema = "imdb", catalog = "")
@IdClass(HadRoleEntityPK.class)
public class HadRoleEntity {
    @Id
    @Column(name = "title_id")
    private String titleId;
    @Id
    @Column(name = "name_id")
    private String nameId;
    @Id
    @Column(name = "role_")
    private String role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HadRoleEntity that = (HadRoleEntity) o;

        if (titleId != null ? !titleId.equals(that.titleId) : that.titleId != null) return false;
        if (nameId != null ? !nameId.equals(that.nameId) : that.nameId != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = titleId != null ? titleId.hashCode() : 0;
        result = 31 * result + (nameId != null ? nameId.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
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
