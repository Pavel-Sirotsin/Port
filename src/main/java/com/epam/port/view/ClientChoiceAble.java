package com.epam.port.view;

import java.util.Map;
import java.util.regex.Pattern;

public interface ClientChoiceAble {
    String PRICE_MESSAGE = "Enter the maximum voucher price acceptable for YOU:";
    String SORRY_PRICE_MESSAGE = "Sorry, but the maximum cost of a voucher is a 3500$! Try again, please!";
    String INVALID_PRICE_MESSAGE = "Invalid price value.";

    String VOUCHER_MESSAGE = "Choose and Enter voucher type from list: Trip, Vacation, Shopping, Excursion:";
    String SORRY_VOUCHER_MESSAGE = "Sorry, but we only offer this type of travel! You can choose from the list!";
    String INVALID_VOUCHER_MESSAGE = "Invalid voucher type value.";

    String DAYS_MESSAGE = "Enter the number of days you want to travel:";
    String SORRY_DAYS_MESSAGE = "Sorry, but the maximum number of days is 20! Try again, please!";
    String INVALID_DAYS_MESSAGE = "Invalid the number of days value.";

    String COUNTRY_MESSAGE = "Enter country name you want to travel:\nSPAIN, ITALY, CYPRUS, TURKEY, AFGHANISTAN" +
            "\nCZECH REPUBLIC, USA, BELARUS, ISRAEL, GERMANY" +
            "\nARGENTINA, ENGLAND, MEXICO, SYRIA, ETHIOPIA";
    String SORRY_COUNTRY_MESSAGE = "Sorry, but we do not have trips to this country! Try again, please!";
    String INVALID_COUNTRY_MESSAGE = "Invalid country name.";

    Pattern PRICE = Pattern.compile("[\\d.]{0,4}");
    Pattern TYPE = Pattern.compile("(Trip|Vacation|Shopping|Excursion){1}", Pattern.CASE_INSENSITIVE);
    Pattern DAYS = Pattern.compile("[0-9]{0,2}");
    Pattern COUNTRY = Pattern.compile("(spain|italy|cyprus|turkey|afghanistan" +
            "|usa|belarus|israel|germany|argentina" +
            "|england|mexico|syria|ethiopia|czech republic){1}");

    Map<String, String> shipRegistration();

}
