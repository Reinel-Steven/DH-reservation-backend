package com.reist.reservation.utils;

import java.util.Arrays;
import java.util.List;

public class StringConverter {

    public static String convertListToString(List<String> list) {
        if (list == null || list.isEmpty()) {
            return ""; // Devuelve un String vacío si la lista es nula o está vacía
        }
        return String.join(";", list); // Une los elementos de la lista con ";"
    }

    public static List<String> convertStringToList(String input) {
        if (input == null || input.trim().isEmpty()) {
            return List.of(); // Devuelve una lista vacía si el input es nulo o vacío
        }
        return Arrays.asList(input.split(";")); // Divide el String por ";" y convierte a List
    }
}
