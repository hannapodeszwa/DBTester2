package pl.polsl.dbtester.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "title_ratings", schema = "imdb", catalog = "")
public class TitleRatingsEntity {
    @Id
    @Column(name = "title_id")
    private String titleId;
    @Basic
    @Column(name = "average_rating")
    private Double averageRating;
    @Basic
    @Column(name = "num_votes")
    private Integer numVotes;
    @OneToOne
    @JoinColumn(name = "title_id", referencedColumnName = "title_id", nullable = false)
    private TitlesEntity titlesByTitleId;

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(Integer numVotes) {
        this.numVotes = numVotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TitleRatingsEntity that = (TitleRatingsEntity) o;

        if (titleId != null ? !titleId.equals(that.titleId) : that.titleId != null) return false;
        if (averageRating != null ? !averageRating.equals(that.averageRating) : that.averageRating != null)
            return false;
        if (numVotes != null ? !numVotes.equals(that.numVotes) : that.numVotes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = titleId != null ? titleId.hashCode() : 0;
        result = 31 * result + (averageRating != null ? averageRating.hashCode() : 0);
        result = 31 * result + (numVotes != null ? numVotes.hashCode() : 0);
        return result;
    }

    public TitlesEntity getTitlesByTitleId() {
        return titlesByTitleId;
    }

    public void setTitlesByTitleId(TitlesEntity titlesByTitleId) {
        this.titlesByTitleId = titlesByTitleId;
    }
}
