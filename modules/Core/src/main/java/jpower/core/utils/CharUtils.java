package jpower.core.utils;

import jpower.core.Alphabet;

import java.util.Random;

public class CharUtils {
    public static char random() {
        return Alphabet.uppercase()[new Random().nextInt(26)];
    }
}
