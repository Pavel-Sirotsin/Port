package com.epam.port.repository.data_gen_impl;

import com.epam.port.repository.DataGeneratorAble;
import com.epam.port.repository.model.ship.Ship;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShipDataGenerator implements DataGeneratorAble {

    private String genName() {
        String[] names = {"Patrycja", "Armstrong", "Rabia", "Morin"
                , "Macy", "Dickinson", "Asia", "Finney", "Carla"
                , "Vance", "Harriett", "Hutton", "Keisha", "Goodman"
                , "Jozef", " Richmond", "Felicity", " Gunn", "Marianna"};
        return names[RANDOM.nextInt(20)];
    }

    private String genOwner() {
        String[] owners = {"Unifeeder A/S", "MAERSK A/S"
                , "MSC Mediterranean Shipping Company", "Cosco Shipping Lines Finland", "3p logistics, JSC"
        ,"ABS Contractor, JSC", " AFALITA, JSC", "Litinspektus, JSC", "Viasea Shipping"};
        return owners[RANDOM.nextInt(7)];
    }

    private String genDestination() {
        String[] lines = {"Riga", "Bremerhaven", "Hamburg", "Rotterdam"
                , "Szczecin", "Immingham", "Felixstowe", "Muuga", "Dunkirk"
                , "Helsingborg", "Kaliningrad", "Norrköping", "Kiel Canal"};
        return lines[RANDOM.nextInt(13)];
    }


    @Override
    public List<Ship> generate() {
        return IntStream
                .range(0, 20)
                .mapToObj(i -> new Ship(genName(), genOwner(), genDestination()))
                .collect(Collectors.toList());
    }


}
