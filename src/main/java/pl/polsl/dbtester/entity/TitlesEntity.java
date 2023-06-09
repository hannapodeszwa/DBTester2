package pl.polsl.dbtester.entity;

//import jakarta.persistence.*;
import javax.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "titles", schema = "imdb", catalog = "")
public class TitlesEntity {
    @Id
    @Column(name = "title_id")
    private String titleId;
    @Basic
    @Column(name = "title_type")
    private String titleType;
    @Basic
    @Column(name = "primary_title")
    private String primaryTitle;
    @Basic
    @Column(name = "original_title")
    private String originalTitle;
    @Basic
    @Column(name = "is_adult")
    private Boolean isAdult;
    @Basic
    @Column(name = "start_year")
    private Integer startYear;
    @Basic
    @Column(name = "end_year")
    private Integer endYear;
    @Basic
    @Column(name = "runtime_minutes")
    private Integer runtimeMinutes;
    @OneToMany(mappedBy = "titlesByTitleId")
    private Collection<AliasAttributesEntity> aliasAttributesByTitleId;
    @OneToMany(mappedBy = "titlesByTitleId")
    private Collection<AliasTypesEntity> aliasTypesByTitleId;
    @OneToMany(mappedBy = "titlesByTitleId")
    private Collection<AliasesEntity> aliasesByTitleId;
    @OneToMany(mappedBy = "titlesByTitleId")
    private Collection<DirectorsEntity> directorsByTitleId;
//    @OneToOne(mappedBy = "titlesByEpisodeTitleId")
//    private EpisodeBelongsToEntity episodeBelongsToByTitleId;
//    @OneToMany(mappedBy = "titlesByParentTvShowTitleId")
//    private Collection<EpisodeBelongsToEntity> episodeBelongsTosByTitleId;
    @OneToMany(mappedBy = "titlesByTitleId")
    private Collection<HadRoleEntity> hadRolesByTitleId;
    @OneToMany(mappedBy = "titlesByTitleId")
    private Collection<KnownForEntity> knownForsByTitleId;
    @OneToMany(mappedBy = "titlesByTitleId")
    private Collection<PrincipalsEntity> principalsByTitleId;
    @OneToMany(mappedBy = "titlesByTitleId")
    private Collection<TitleGenresEntity> titleGenresByTitleId;
    @OneToOne(mappedBy = "titlesByTitleId")
    private TitleRatingsEntity titleRatingsByTitleId;
    @OneToMany(mappedBy = "titlesByTitleId")
    private Collection<WritersEntity> writersByTitleId;

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Boolean getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(Boolean isAdult) {
        this.isAdult = isAdult;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Integer getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public void setRuntimeMinutes(Integer runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TitlesEntity that = (TitlesEntity) o;

        if (titleId != null ? !titleId.equals(that.titleId) : that.titleId != null) return false;
        if (titleType != null ? !titleType.equals(that.titleType) : that.titleType != null) return false;
        if (primaryTitle != null ? !primaryTitle.equals(that.primaryTitle) : that.primaryTitle != null) return false;
        if (originalTitle != null ? !originalTitle.equals(that.originalTitle) : that.originalTitle != null)
            return false;
        if (isAdult != null ? !isAdult.equals(that.isAdult) : that.isAdult != null) return false;
        if (startYear != null ? !startYear.equals(that.startYear) : that.startYear != null) return false;
        if (endYear != null ? !endYear.equals(that.endYear) : that.endYear != null) return false;
        if (runtimeMinutes != null ? !runtimeMinutes.equals(that.runtimeMinutes) : that.runtimeMinutes != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = titleId != null ? titleId.hashCode() : 0;
        result = 31 * result + (titleType != null ? titleType.hashCode() : 0);
        result = 31 * result + (primaryTitle != null ? primaryTitle.hashCode() : 0);
        result = 31 * result + (originalTitle != null ? originalTitle.hashCode() : 0);
        result = 31 * result + (isAdult != null ? isAdult.hashCode() : 0);
        result = 31 * result + (startYear != null ? startYear.hashCode() : 0);
        result = 31 * result + (endYear != null ? endYear.hashCode() : 0);
        result = 31 * result + (runtimeMinutes != null ? runtimeMinutes.hashCode() : 0);
        return result;
    }

    public Collection<AliasAttributesEntity> getAliasAttributesByTitleId() {
        return aliasAttributesByTitleId;
    }

    public void setAliasAttributesByTitleId(Collection<AliasAttributesEntity> aliasAttributesByTitleId) {
        this.aliasAttributesByTitleId = aliasAttributesByTitleId;
    }

    public Collection<AliasTypesEntity> getAliasTypesByTitleId() {
        return aliasTypesByTitleId;
    }

    public void setAliasTypesByTitleId(Collection<AliasTypesEntity> aliasTypesByTitleId) {
        this.aliasTypesByTitleId = aliasTypesByTitleId;
    }

    public Collection<AliasesEntity> getAliasesByTitleId() {
        return aliasesByTitleId;
    }

    public void setAliasesByTitleId(Collection<AliasesEntity> aliasesByTitleId) {
        this.aliasesByTitleId = aliasesByTitleId;
    }

    public Collection<DirectorsEntity> getDirectorsByTitleId() {
        return directorsByTitleId;
    }

    public void setDirectorsByTitleId(Collection<DirectorsEntity> directorsByTitleId) {
        this.directorsByTitleId = directorsByTitleId;
    }

//    public EpisodeBelongsToEntity getEpisodeBelongsToByTitleId() {
//        return episodeBelongsToByTitleId;
//    }

//    public void setEpisodeBelongsToByTitleId(EpisodeBelongsToEntity episodeBelongsToByTitleId) {
//        this.episodeBelongsToByTitleId = episodeBelongsToByTitleId;
//    }

//    public Collection<EpisodeBelongsToEntity> getEpisodeBelongsTosByTitleId() {
//        return episodeBelongsTosByTitleId;
//    }

//    public void setEpisodeBelongsTosByTitleId(Collection<EpisodeBelongsToEntity> episodeBelongsTosByTitleId) {
//        this.episodeBelongsTosByTitleId = episodeBelongsTosByTitleId;
//    }

    public Collection<HadRoleEntity> getHadRolesByTitleId() {
        return hadRolesByTitleId;
    }

    public void setHadRolesByTitleId(Collection<HadRoleEntity> hadRolesByTitleId) {
        this.hadRolesByTitleId = hadRolesByTitleId;
    }

    public Collection<KnownForEntity> getKnownForsByTitleId() {
        return knownForsByTitleId;
    }

    public void setKnownForsByTitleId(Collection<KnownForEntity> knownForsByTitleId) {
        this.knownForsByTitleId = knownForsByTitleId;
    }

    public Collection<PrincipalsEntity> getPrincipalsByTitleId() {
        return principalsByTitleId;
    }

    public void setPrincipalsByTitleId(Collection<PrincipalsEntity> principalsByTitleId) {
        this.principalsByTitleId = principalsByTitleId;
    }

    public Collection<TitleGenresEntity> getTitleGenresByTitleId() {
        return titleGenresByTitleId;
    }

    public void setTitleGenresByTitleId(Collection<TitleGenresEntity> titleGenresByTitleId) {
        this.titleGenresByTitleId = titleGenresByTitleId;
    }

    public TitleRatingsEntity getTitleRatingsByTitleId() {
        return titleRatingsByTitleId;
    }

    public void setTitleRatingsByTitleId(TitleRatingsEntity titleRatingsByTitleId) {
        this.titleRatingsByTitleId = titleRatingsByTitleId;
    }

    public Collection<WritersEntity> getWritersByTitleId() {
        return writersByTitleId;
    }

    public void setWritersByTitleId(Collection<WritersEntity> writersByTitleId) {
        this.writersByTitleId = writersByTitleId;
    }
}
