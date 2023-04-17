package pl.polsl.dbtester.model;

import pl.polsl.dbtester.entity.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvReader {
    private Mapper mapper = new Mapper();

    public void readCsv(String fileName, List<TitlesEntity> titles, List<TitleGenresEntity> titleGenres, List<TitleRatingsEntity> titleRatings,
                        List<AliasAttributesEntity> aliasAttributes, List<AliasesEntity> aliases, List<AliasTypesEntity> aliasTypes) {

        readTitles(fileName, titles);
        readGenres(fileName, titleGenres);


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
}
