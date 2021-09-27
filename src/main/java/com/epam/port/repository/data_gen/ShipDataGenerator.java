package com.epam.port.repository.data_gen;

import com.epam.port.repository.model.ship.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShipDataGenerator {
    private static final Logger logger = LogManager.getLogger(ShipDataGenerator.class);

    private static final int AMOUNT = 10;
    private final Random random = new Random();

    private String genName(int i) {
        logger.traceEntry("genName" + i);
        String[] names = {"Patrycja", "Armstrong", "Rabia", "Morin"
                , "Macy", "Dickinson", "Asia", "Finney", "Carla"
                , "Vance", "Harriett", "Hutton", "Keisha", "Goodman"
                , "Jozef", "Richmond", "Felicity", "Gunn", "Marianna", "Victory"};
        logger.traceExit(names[i]);
        return names[i];
    }

    private String genOwner() {
        logger.traceEntry("genOwner");
        String[] owners = {"Unified A/S", "MAERSK A/S"
                , "MSC", "Cosmo S/L", "3p logistics, JSC"
                , "ABS Contractor, JSC", "AFFLICT, JSC", "Litinspektus, JSC", "Viasea Shipping"};
        String result = owners[random.nextInt(owners.length)];
        logger.traceExit(result);
        return result;
    }

    private String genDestination() {
        logger.traceEntry("genDestination");
        String[] lines = {"Riga", "Bremerhaven", "Hamburg", "Rotterdam"
                , "Szczecin", "Immingham", "Felixstowe", "Muuga", "Dunkirk"
                , "Helsingborg", "Kaliningrad", "Norrköping", "Kiel Canal"};
        String result = lines[random.nextInt(lines.length)];
        logger.traceExit(result);
        return result;
    }


    public List<Ship> generate() {
        logger.traceEntry("generate");
        return IntStream
                .range(0, AMOUNT)
                .mapToObj(i -> new Ship(genName(i), genOwner(), genDestination()))
                .collect(Collectors.toList());
    }


}
