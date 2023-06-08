package pl.polsl.dbtester.model;

import org.hibernate.bytecode.enhance.internal.tracker.DirtyTracker;
import pl.polsl.dbtester.entity.*;

public class Mapper {
    public TitlesEntity createTitleEntity(String[] attributes) {
        TitlesEntity titlesEntity = new TitlesEntity();
        titlesEntity.setTitleId(attributes[0]);
        titlesEntity.setTitleType(attributes[1]);
        titlesEntity.setPrimaryTitle(attributes[2]);
        titlesEntity.setOriginalTitle(attributes[3]);
        titlesEntity.setIsAdult(Boolean.parseBoolean(attributes[4]));
        titlesEntity.setStartYear(toInt(attributes[5]));
        titlesEntity.setEndYear(toInt(attributes[6]));
        titlesEntity.setRuntimeMinutes(toInt(attributes[7]));

        return titlesEntity;
    }

    public TitleRatingsEntity createTitleRatingsEntity(String[] attributes) {
        TitleRatingsEntity titleRatingsEntity = new TitleRatingsEntity();
        titleRatingsEntity.setTitleId(attributes[0]);
        titleRatingsEntity.setAverageRating(toDouble(attributes[1]));
        titleRatingsEntity.setNumVotes(toInt(attributes[2]));

        return titleRatingsEntity;
    }

    public TitleGenresEntity createTitleGenresEntity(String[] attributes) {
        TitleGenresEntity titleGenresEntity = new TitleGenresEntity();
        titleGenresEntity.setTitleId(attributes[0]);
        titleGenresEntity.setGenre(attributes[1]);

        return titleGenresEntity;
    }

    public AliasesEntity createAliasesEntity(String[] attributes) {
        String region = attributes[3];
        if (region.length() >= 4) {
            region = region.substring(0, 4);
        }
        String language = attributes[4];
        if (language.length() >= 4) {
            language = language.substring(0, 4);
        }
        AliasesEntity aliasesEntity = new AliasesEntity();
        aliasesEntity.setTitleId(attributes[0]);
        aliasesEntity.setOrdering(toInt(attributes[1]));
        aliasesEntity.setTitle(attributes[2]);
        aliasesEntity.setRegion(region);
        aliasesEntity.setLanguage(language);
        if (attributes.length >= 6) {
            aliasesEntity.setIsOriginalTitle(toBoolean(attributes[5]));
        } else {
            aliasesEntity.setIsOriginalTitle(Boolean.parseBoolean("0"));
        }


        return aliasesEntity;
    }

    public AliasAttributesEntity createAliasAttributesEntity(String[] attributes) {
        AliasAttributesEntity aliasAttributesEntity = new AliasAttributesEntity();
        aliasAttributesEntity.setTitleId(attributes[0]);
        aliasAttributesEntity.setOrdering(toInt(attributes[1]));
        aliasAttributesEntity.setAttribute(attributes[2]);

        return aliasAttributesEntity;
    }

    public AliasTypesEntity createAliasTypesEntity(String[] attributes) {
        AliasTypesEntity aliasTypesEntity = new AliasTypesEntity();
        aliasTypesEntity.setTitleId(attributes[0]);
        aliasTypesEntity.setOrdering(toInt(attributes[1]));
        aliasTypesEntity.setType(attributes[2]);

        return aliasTypesEntity;
    }

    public DirectorsEntity createDirectorsEntity(String[] attributes) {
        DirectorsEntity directorsEntity = new DirectorsEntity();
        directorsEntity.setTitleId(attributes[0]);
        directorsEntity.setNameId(attributes[1]);

        return directorsEntity;
    }

    public EpisodeBelongsToEntity createEpisodeBelongsToEntity(String[] attributes) {
        EpisodeBelongsToEntity episodeBelongsToEntity = new EpisodeBelongsToEntity();
        episodeBelongsToEntity.setEpisodeTitleId(attributes[0]);
        episodeBelongsToEntity.setParentTvShowTitleId(attributes[1]);
        episodeBelongsToEntity.setSeasonNumber(toInt(attributes[2]));
        episodeBelongsToEntity.setEpisodeNumber(toInt(attributes[3]));

        return episodeBelongsToEntity;
    }

    public HadRoleEntity createHadRoleEntity(String[] attributes) {
        HadRoleEntity hadRoleEntity = new HadRoleEntity();
        hadRoleEntity.setTitleId(attributes[0]);
        hadRoleEntity.setNameId(attributes[1]);
        if (attributes.length >= 3) {
            hadRoleEntity.setRole(attributes[2]);
        } else {
            hadRoleEntity.setRole("");
        }

        return hadRoleEntity;
    }

    public KnownForEntity createKnownForEntity(String[] attributes) {
        KnownForEntity knownForEntity = new KnownForEntity();
        knownForEntity.setNameId(attributes[0]);
        knownForEntity.setTitleId(attributes[1]);

        return knownForEntity;
    }

    public NamesEntity createNamesEntity(String[] attributes) {
        NamesEntity namesEntity = new NamesEntity();
        namesEntity.setNameId(attributes[0]);
        namesEntity.setName(attributes[1]);
        namesEntity.setBirthYear(toShort(attributes[2]));
        namesEntity.setDeathYear(toShort(attributes[3]));

        return namesEntity;
    }

    public NameWorkedAsEntity createNameWorkedAsEntity(String[] attributes) {
        NameWorkedAsEntity nameWorkedAsEntity = new NameWorkedAsEntity();
        nameWorkedAsEntity.setNameId(attributes[0]);
        nameWorkedAsEntity.setProfession(attributes[1]);

        return nameWorkedAsEntity;
    }

    public PrincipalsEntity createPrincipalsEntity(String[] attributes) {
        PrincipalsEntity principalsEntity = new PrincipalsEntity();
        principalsEntity.setTitleId(attributes[0]);
        principalsEntity.setOrdering(toByte(attributes[1]));
        principalsEntity.setNameId(attributes[2]);
        principalsEntity.setJobCategory(attributes[3]);
        principalsEntity.setJob(attributes[4]);

        return principalsEntity;
    }

    public WritersEntity createWritersEntity(String[] attributes) {
        WritersEntity writersEntity = new WritersEntity();
        writersEntity.setTitleId(attributes[0]);
        writersEntity.setNameId(attributes[1]);

        return writersEntity;
    }

    Integer toInt(String attribute) {
        if (attribute.equals("\\N")) return null;
        else return Integer.parseInt(attribute);
    }

    Double toDouble(String attribute) {
        if (attribute.equals("\\N")) return null;
        else return Double.parseDouble(attribute);
    }

    Short toShort(String attribute) {
        if (attribute.equals("\\N")) return null;
        else return Short.parseShort(attribute);
    }

    Byte toByte(String attribute) {
        if (attribute.equals("\\N")) return null;
        else return Byte.parseByte(attribute);
    }

    Boolean toBoolean(String attribute) {
        if (attribute.equals("\\N")) return null;
        else return Boolean.parseBoolean(attribute);
    }
}
