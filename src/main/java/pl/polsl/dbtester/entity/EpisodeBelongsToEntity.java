package pl.polsl.dbtester.entity;

//import jakarta.persistence.*;
import javax.persistence.*;
@Entity
@Table(name = "episode_belongs_to", schema = "imdb", catalog = "")
public class EpisodeBelongsToEntity {
    @Id
    @Column(name = "episode_title_id")
    private String episodeTitleId;
    @Basic
    @Column(name = "parent_tv_show_title_id")
    private String parentTvShowTitleId;
    @Basic
    @Column(name = "season_number")
    private Integer seasonNumber;
    @Basic
    @Column(name = "episode_number")
    private Integer episodeNumber;
    @OneToOne
    @JoinColumn(name = "episode_title_id", referencedColumnName = "title_id", nullable = false)
    private TitlesEntity titlesByEpisodeTitleId;
    @ManyToOne
    @JoinColumn(name = "parent_tv_show_title_id", referencedColumnName = "title_id", nullable = false, insertable = false, updatable = false)
    private TitlesEntity titlesByParentTvShowTitleId;

    public String getEpisodeTitleId() {
        return episodeTitleId;
    }

    public void setEpisodeTitleId(String episodeTitleId) {
        this.episodeTitleId = episodeTitleId;
    }

    public String getParentTvShowTitleId() {
        return parentTvShowTitleId;
    }

    public void setParentTvShowTitleId(String parentTvShowTitleId) {
        this.parentTvShowTitleId = parentTvShowTitleId;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EpisodeBelongsToEntity that = (EpisodeBelongsToEntity) o;

        if (episodeTitleId != null ? !episodeTitleId.equals(that.episodeTitleId) : that.episodeTitleId != null)
            return false;
        if (parentTvShowTitleId != null ? !parentTvShowTitleId.equals(that.parentTvShowTitleId) : that.parentTvShowTitleId != null)
            return false;
        if (seasonNumber != null ? !seasonNumber.equals(that.seasonNumber) : that.seasonNumber != null) return false;
        if (episodeNumber != null ? !episodeNumber.equals(that.episodeNumber) : that.episodeNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = episodeTitleId != null ? episodeTitleId.hashCode() : 0;
        result = 31 * result + (parentTvShowTitleId != null ? parentTvShowTitleId.hashCode() : 0);
        result = 31 * result + (seasonNumber != null ? seasonNumber.hashCode() : 0);
        result = 31 * result + (episodeNumber != null ? episodeNumber.hashCode() : 0);
        return result;
    }

    public TitlesEntity getTitlesByEpisodeTitleId() {
        return titlesByEpisodeTitleId;
    }

    public void setTitlesByEpisodeTitleId(TitlesEntity titlesByEpisodeTitleId) {
        this.titlesByEpisodeTitleId = titlesByEpisodeTitleId;
    }

    public TitlesEntity getTitlesByParentTvShowTitleId() {
        return titlesByParentTvShowTitleId;
    }

    public void setTitlesByParentTvShowTitleId(TitlesEntity titlesByParentTvShowTitleId) {
        this.titlesByParentTvShowTitleId = titlesByParentTvShowTitleId;
    }
}
