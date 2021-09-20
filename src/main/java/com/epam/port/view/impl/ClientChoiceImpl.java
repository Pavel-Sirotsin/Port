package com.epam.port.view.impl;


import com.epam.travel.view.ClientChoiceAble;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ClientChoiceImpl implements ClientChoiceAble {
    private static final Logger logger = LogManager.getLogger(ClientChoiceImpl.class);

    @Override
    public Map<String, String> chooseByPriceDaysType() {
        Map<String, String> clientChoice = new HashMap<>();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(PRICE_MESSAGE);
            while (!scanner.hasNext(PRICE)) {
                System.err.println(SORRY_PRICE_MESSAGE);
                logger.warn(INVALID_PRICE_MESSAGE);
                scanner.next();
            }
            String clientPriceInput = scanner.next();
            clientChoice.put("clientRequiredPrice", clientPriceInput);

            System.out.println(VOUCHER_MESSAGE);
            while (!scanner.hasNext(TYPE)) {
                System.err.println(SORRY_VOUCHER_MESSAGE);
                logger.warn(INVALID_VOUCHER_MESSAGE);
                scanner.next();
            }
            String clientVoucherInput = scanner.next();
            clientChoice.put("voucher", clientVoucherInput);

            System.out.println(DAYS_MESSAGE);
            while (!scanner.hasNext(DAYS)) {
                System.err.println(SORRY_DAYS_MESSAGE);
                logger.warn(INVALID_DAYS_MESSAGE);
                scanner.next();
            }
            String clientDaysInput = scanner.next();
            clientChoice.put("numberDays", clientDaysInput);

        }
        return clientChoice;
    }

    @Override
    public Map<String, String> chooseByCountry() {
        Map<String, String> clientChoice = new HashMap<>();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(COUNTRY_MESSAGE);
            while (!scanner.hasNext(COUNTRY)) {
                System.err.println(SORRY_COUNTRY_MESSAGE);
                logger.warn(INVALID_COUNTRY_MESSAGE);
                scanner.next();
            }
            String clientPriceInput = scanner.next();
            clientChoice.put("country", clientPriceInput);
            return clientChoice;
        }


    }
}
