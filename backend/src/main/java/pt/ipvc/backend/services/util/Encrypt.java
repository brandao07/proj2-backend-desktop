package pt.ipvc.backend.services.util;

import org.jetbrains.annotations.NotNull;


public class Encrypt {
    private static final int key = 5;

    @NotNull
    public static String encrypt(@NotNull String value) {
        char[] chars = value.toCharArray();
        StringBuilder str = new StringBuilder();

        for (char c : chars)
            str.append((char) (c + key));

        return String.valueOf(str);
    }

    @NotNull
    public static String decrypt(@NotNull String value) {
        char[] chars = value.toCharArray();
        StringBuilder str = new StringBuilder();

        for (char c : chars)
            str.append((char) (c - key));

        return String.valueOf(str);
    }
}
