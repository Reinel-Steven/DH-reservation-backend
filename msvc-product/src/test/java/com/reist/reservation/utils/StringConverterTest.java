package com.reist.reservation.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringConverterTest {

    @Test
    void testConvertListToString_NonEmptyList() {
        List<String> input = Arrays.asList("apple", "banana", "cherry");
        String result = StringConverter.convertListToString(input);
        assertEquals("apple;banana;cherry", result);
    }

    @Test
    void testConvertListToString_EmptyList() {
        List<String> input = List.of();
        String result = StringConverter.convertListToString(input);
        assertEquals("", result);
    }

    @Test
    void testConvertListToString_NullList() {
        String result = StringConverter.convertListToString(null);
        assertEquals("", result);
    }

    @Test
    void testConvertStringToList_NonEmptyString() {
        String input = "apple;banana;cherry";
        List<String> result = StringConverter.convertStringToList(input);
        assertEquals(Arrays.asList("apple", "banana", "cherry"), result);
    }

    @Test
    void testConvertStringToList_EmptyString() {
        String input = "";
        List<String> result = StringConverter.convertStringToList(input);
        assertTrue(result.isEmpty());
    }

    @Test
    void testConvertStringToList_NullString() {
        List<String> result = StringConverter.convertStringToList(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testConvertStringToList_StringWithSpaces() {
        String input = "  ";
        List<String> result = StringConverter.convertStringToList(input);
        assertTrue(result.isEmpty());
    }
}