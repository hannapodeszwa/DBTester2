package pl.polsl.dbtester.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "principals", schema = "imdb", catalog = "")
@IdClass(PrincipalsEntityPK.class)
public class PrincipalsEntity {
    @Id
    @Column(name = "title_id")
    private String titleId;
    @Id
    @Column(name = "ordering")
    private byte ordering;
    @Basic
    @Column(name = "name_id")
    private String nameId;
    @Basic
    @Column(name = "job_category")
    private String jobCategory;
    @Basic
    @Column(name = "job")
    private String job;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
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

    public byte getOrdering() {
        return ordering;
    }

    public void setOrdering(byte ordering) {
        this.ordering = ordering;
    }

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrincipalsEntity that = (PrincipalsEntity) o;

        if (ordering != that.ordering) return false;
        if (titleId != null ? !titleId.equals(that.titleId) : that.titleId != null) return false;
        if (nameId != null ? !nameId.equals(that.nameId) : that.nameId != null) return false;
        if (jobCategory != null ? !jobCategory.equals(that.jobCategory) : that.jobCategory != null) return false;
        if (job != null ? !job.equals(that.job) : that.job != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = titleId != null ? titleId.hashCode() : 0;
        result = 31 * result + (int) ordering;
        result = 31 * result + (nameId != null ? nameId.hashCode() : 0);
        result = 31 * result + (jobCategory != null ? jobCategory.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
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
