package com.epam.port.repository.data_gen;

import com.epam.port.repository.model.ship.Ship;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShipDataGenerator {
    private static final int AMOUNT = 10;

    private String genName(int i) {
        String[] names = {"Patrycja", "Armstrong", "Rabia", "Morin"
                , "Macy", "Dickinson", "Asia", "Finney", "Carla"
                , "Vance", "Harriett", "Hutton", "Keisha", "Goodman"
                , "Jozef", "Richmond", "Felicity", "Gunn", "Marianna","Victory"};
        return names[i];
    }

    private String genOwner() {
        String[] owners = {"Unifeeder A/S", "MAERSK A/S"
                , "MSC", "Cosco S/L", "3p logistics, JSC"
        ,"ABS Contractor, JSC", "AFALITA, JSC", "Litinspektus, JSC", "Viasea Shipping"};
        return owners[new Random().nextInt(7)];
    }

    private String genDestination() {
        String[] lines = {"Riga", "Bremerhaven", "Hamburg", "Rotterdam"
                , "Szczecin", "Immingham", "Felixstowe", "Muuga", "Dunkirk"
                , "Helsingborg", "Kaliningrad", "Norrköping", "Kiel Canal"};
        return lines[new Random().nextInt(13)];
    }


    public List<Ship> generate() {
        return IntStream
                .range(0, AMOUNT)
                .mapToObj(i -> new Ship(genName(i), genOwner(), genDestination()))
                .collect(Collectors.toList());
    }


}
