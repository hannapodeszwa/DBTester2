package pl.polsl.dbtester.model;

import pl.polsl.dbtester.entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvReader {
    private Mapper mapper = new Mapper();

    public void readCsv(String fileName,
                        List<AliasAttributesEntity> aliasAttributes,
                        List<AliasTypesEntity> aliasTypes,
                        List<AliasesEntity> aliases,
                        List<DirectorsEntity> directors,
                        List<EpisodeBelongsToEntity> episodeBelongsTo,
                        List<HadRoleEntity> hadRole,
                        List<KnownForEntity> knownFor,
                        List<NameWorkedAsEntity> nameWorkedAs,
                        List<NamesEntity> names,
                        List<PrincipalsEntity> principals,
                        List<TitleGenresEntity> titleGenres,
                        List<TitleRatingsEntity> titleRatings,
                        List<TitlesEntity> titles,
                        List<WritersEntity> writers ) {

        readAliasAttributes(fileName, aliasAttributes);
        readAliasTypes(fileName, aliasTypes);
        readAliases(fileName, aliases);

        readTitles(fileName, titles);
        readGenres(fileName, titleGenres);
        readRatings(fileName, titleRatings);


    }

    void readAliasAttributes(String fileName, List<AliasAttributesEntity> list) {
        // ALIAS ATTRIBUTES /////////////////////////////////////
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Alias_attributes.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                AliasAttributesEntity l = mapper.createAliasAttributesEntity(attributes);

                list.add(l);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    void readAliasTypes(String fileName, List<AliasTypesEntity> list) {
        // ALIAS TYPES /////////////////////////////////////
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Alias_types.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                AliasTypesEntity l = mapper.createAliasTypesEntity(attributes);

                list.add(l);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    void readAliases(String fileName, List<AliasesEntity> list) {
        // ALIASES /////////////////////////////////////
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Aliases.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                AliasesEntity l = mapper.createAliasesEntity(attributes);

                list.add(l);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    void readTitles(String fileName, List<TitlesEntity> titles) {
        // TITLES /////////////////////////////////////
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Titles.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                TitlesEntity title = mapper.createTitleEntity(attributes);

                titles.add(title);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    void readGenres(String fileName, List<TitleGenresEntity> titleGenres) {
        // GENRES /////////////////////////////////////
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Title_genres.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                TitleGenresEntity title = mapper.createTitleGenresEntity(attributes);

                titleGenres.add(title);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    void readRatings(String fileName, List<TitleRatingsEntity> list) {
        // RATINGS /////////////////////////////////////
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Title_ratings.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                TitleRatingsEntity l = mapper.createTitleRatingsEntity(attributes);

                list.add(l);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
