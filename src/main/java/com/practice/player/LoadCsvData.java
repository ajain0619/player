package com.practice.player;


import com.practice.player.model.Player;
import com.practice.player.repository.PlayerRepository;
import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class LoadCsvData {

    @Autowired
    private PlayerRepository playerRepository;

    @PostConstruct
    public void loadCsv() {

        ClassPathResource resource = new ClassPathResource("players.csv");
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {

            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            //CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            for (CSVRecord record : csvParser) {
                convertToPlayer(record);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void convertToPlayer(CSVRecord record) {
        Player player = new Player();
        player.setPlayerId(record.get("playerId"));

        Integer birthYear = record.get("birthYear").isEmpty() ? 0 : Integer.parseInt(record.get("birthYear"));
        player.setBirthYear(birthYear);

        Integer birthMonth = record.get("birthMonth").isEmpty() ? 0 : Integer.parseInt(record.get("birthMonth"));
        player.setBirthMonth(birthMonth);

        Integer birthDay = record.get("birthDay").isEmpty() ? 0 : Integer.parseInt(record.get("birthDay"));
        player.setBirthDay(birthDay);

        player.setBirthCountry(record.get("birthCountry"));
        player.setBirthState(record.get("birthState"));
        player.setBirthCity(record.get("birthCity"));

        Integer deathYear = record.get("deathYear").isEmpty() ? 0 : Integer.parseInt(record.get("deathYear"));
        player.setDeathYear(deathYear);

        Integer deathMonth = record.get("deathMonth").isEmpty() ? 0 : Integer.parseInt(record.get("deathMonth"));
        player.setDeathMonth(deathMonth);

        Integer deathDay = record.get("deathDay").isEmpty() ? 0 : Integer.parseInt(record.get("deathDay"));
        player.setDeathDay(deathDay);

        player.setDeathCountry(record.get("deathCountry"));
        player.setDeathState(record.get("deathState"));
        player.setDeathCity(record.get("deathCity"));

        player.setNameFirst(record.get("nameFirst"));
        player.setNameLast(record.get("nameLast"));
        player.setNameGiven(record.get("nameGiven"));

//        player.setWeight(record.get("weight").isEmpty() ? null : Integer.parseInt(record.get("weight")));
//        player.setHeight(record.get("height").isEmpty() ? null : Integer.parseInt(record.get("height")));

        player.setWeight(parseInteger(record.get("weight")));
        player.setHeight(parseInteger(record.get("height")));

        player.setBats(record.get("bats"));
        player.setBbthrows(record.get("bbthrows"));
        player.setDebut(record.get("debut"));
        player.setFinalGame(record.get("finalGame"));
        player.setRetroID(record.get("retroID"));
        player.setBbrefID(record.get("bbrefID"));

        playerRepository.save(player);
    }

    private Integer parseInteger(String value) {
        if (value != null && !value.isEmpty()) {
            return Integer.parseInt(value);
        }
        return 0;
    }
}
