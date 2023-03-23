package pl.polsl.dbtester.entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "names_", schema = "imdb", catalog = "")
public class NamesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "name_id")
    private String nameId;
    @Basic
    @Column(name = "name_")
    private String name;
    @Basic
    @Column(name = "birth_year")
    private Short birthYear;
    @Basic
    @Column(name = "death_year")
    private Short deathYear;
    @OneToMany(mappedBy = "namesByNameId")
    private Collection<DirectorsEntity> directorsByNameId;
    @OneToMany(mappedBy = "namesByNameId")
    private Collection<HadRoleEntity> hadRolesByNameId;
    @OneToMany(mappedBy = "namesByNameId")
    private Collection<KnownForEntity> knownForsByNameId;
    @OneToMany(mappedBy = "namesByNameId")
    private Collection<NameWorkedAsEntity> nameWorkedAsByNameId;
    @OneToMany(mappedBy = "namesByNameId")
    private Collection<PrincipalsEntity> principalsByNameId;
    @OneToMany(mappedBy = "namesByNameId")
    private Collection<WritersEntity> writersByNameId;

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Short birthYear) {
        this.birthYear = birthYear;
    }

    public Short getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Short deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NamesEntity that = (NamesEntity) o;

        if (nameId != null ? !nameId.equals(that.nameId) : that.nameId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (birthYear != null ? !birthYear.equals(that.birthYear) : that.birthYear != null) return false;
        if (deathYear != null ? !deathYear.equals(that.deathYear) : that.deathYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nameId != null ? nameId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthYear != null ? birthYear.hashCode() : 0);
        result = 31 * result + (deathYear != null ? deathYear.hashCode() : 0);
        return result;
    }

    public Collection<DirectorsEntity> getDirectorsByNameId() {
        return directorsByNameId;
    }

    public void setDirectorsByNameId(Collection<DirectorsEntity> directorsByNameId) {
        this.directorsByNameId = directorsByNameId;
    }

    public Collection<HadRoleEntity> getHadRolesByNameId() {
        return hadRolesByNameId;
    }

    public void setHadRolesByNameId(Collection<HadRoleEntity> hadRolesByNameId) {
        this.hadRolesByNameId = hadRolesByNameId;
    }

    public Collection<KnownForEntity> getKnownForsByNameId() {
        return knownForsByNameId;
    }

    public void setKnownForsByNameId(Collection<KnownForEntity> knownForsByNameId) {
        this.knownForsByNameId = knownForsByNameId;
    }

    public Collection<NameWorkedAsEntity> getNameWorkedAsByNameId() {
        return nameWorkedAsByNameId;
    }

    public void setNameWorkedAsByNameId(Collection<NameWorkedAsEntity> nameWorkedAsByNameId) {
        this.nameWorkedAsByNameId = nameWorkedAsByNameId;
    }

    public Collection<PrincipalsEntity> getPrincipalsByNameId() {
        return principalsByNameId;
    }

    public void setPrincipalsByNameId(Collection<PrincipalsEntity> principalsByNameId) {
        this.principalsByNameId = principalsByNameId;
    }

    public Collection<WritersEntity> getWritersByNameId() {
        return writersByNameId;
    }

    public void setWritersByNameId(Collection<WritersEntity> writersByNameId) {
        this.writersByNameId = writersByNameId;
    }
}
