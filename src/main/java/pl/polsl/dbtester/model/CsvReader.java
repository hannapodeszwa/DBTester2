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
        readDirectors(fileName, directors);
        readEpisodeBelongsTo(fileName, episodeBelongsTo);
        readHadRole(fileName, hadRole);
        readKnownFor(fileName, knownFor);
        readNameWorkedAsEntity(fileName, nameWorkedAs);
        readNamesEntity(fileName, names);
        readPrincipalsEntity(fileName, principals);
        readWritersEntity(fileName, writers);

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

    void readDirectors(String fileName, List<DirectorsEntity> list) {
        // DIRECTORS /////////////////////////////////////
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Directors.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                DirectorsEntity l = mapper.createDirectorsEntity(attributes);

                list.add(l);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    void readEpisodeBelongsTo(String fileName, List<EpisodeBelongsToEntity> list) {
        // DIRECTORS /////////////////////////////////////
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Episode_belongs_to.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                EpisodeBelongsToEntity l = mapper.createEpisodeBelongsToEntity(attributes);

                list.add(l);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    void readHadRole(String fileName, List<HadRoleEntity> list) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Had_role.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                HadRoleEntity l = mapper.createHadRoleEntity(attributes);

                list.add(l);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    void readKnownFor(String fileName, List<KnownForEntity> list) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Known_for.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                KnownForEntity l = mapper.createKnownForEntity(attributes);

                list.add(l);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    void readNamesEntity(String fileName, List<NamesEntity> list) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Names_.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                NamesEntity l = mapper.createNamesEntity(attributes);

                list.add(l);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    void readNameWorkedAsEntity(String fileName, List<NameWorkedAsEntity> list) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Name_worked_as.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                NameWorkedAsEntity l = mapper.createNameWorkedAsEntity(attributes);

                list.add(l);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    void readPrincipalsEntity(String fileName, List<PrincipalsEntity> list) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Principals.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                PrincipalsEntity l = mapper.createPrincipalsEntity(attributes);

                list.add(l);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    void readWritersEntity(String fileName, List<WritersEntity> list) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + "/Writers.tsv"))) {
            br.readLine();
            String line = br.readLine();

            while (line != null) {

                String[] attributes = line.split("\t");

                WritersEntity l = mapper.createWritersEntity(attributes);

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
