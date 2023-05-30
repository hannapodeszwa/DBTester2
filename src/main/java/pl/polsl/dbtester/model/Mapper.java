package pl.polsl.dbtester.model;

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
        AliasesEntity aliasesEntity = new AliasesEntity();
        aliasesEntity.setTitleId(attributes[0]);
        aliasesEntity.setOrdering(toInt(attributes[1]));
        aliasesEntity.setTitle(attributes[2]);
        aliasesEntity.setRegion(attributes[3]);
        aliasesEntity.setLanguage(attributes[4]);
        aliasesEntity.setIsOriginalTitle(Byte.parseByte(attributes[5]));

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

    Integer toInt(String attribute) {
        if (attribute.equals("\\N")) return null;
        else return Integer.parseInt(attribute);
    }

    Double toDouble(String attribute) {
        if (attribute.equals("\\N")) return null;
        else return Double.parseDouble(attribute);
    }
}
