package com.attraction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.attraction.history.History;
import com.attraction.history.HistoryRepository;
import com.attraction.monument.Monument;
import com.attraction.monument.MonumentRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Unit test for simple App.
 */
@DataMongoTest
@ExtendWith(SpringExtension.class)
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void createObjectAndGetIt(@Autowired MonumentRepository monumentRepo) {
        Monument object = new Monument(1, "name", 0, 10.0, 20.0, "Nice object", "there will be type", "will be",
                "Warsaw");
        monumentRepo.save(object);
        System.out.println();
        assertEquals(monumentRepo.findMonumentByName("name").getName(), object.getName());
    }

    @Test
    public void checkHistoryCreation(@Autowired HistoryRepository historyRepo) {
        // TODO: to check history correctly we need other instances
    }

    @Test
    public void dummy() {
        assertTrue(true);
    }
}
