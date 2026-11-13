package library;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public final class ConsoleUtil {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    private ConsoleUtil() {
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public static void printError(String text) {
        System.out.println("خطا: " + text);
    }

    public static String readLine(String prompt) {
        System.out.print(prompt + ": ");
        String input = SCANNER.nextLine();
        return input == null ? "" : input.trim();
    }

    public static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt));
            } catch (NumberFormatException e) {
                printError("لطفاً یک عدد صحیح وارد کنید.");
            }
        }
    }

    public static LocalDate readDate(String prompt) {
        while (true) {
            String raw = readLine(prompt + " (قالب yyyy-MM-dd مثل 2026-10-20)");
            try {
                return LocalDate.parse(raw, DATE_FORMAT);
            } catch (DateTimeParseException e) {
                printError("تاریخ وارد شده معتبر نیست.");
            }
        }
    }

    public static int readMenuChoice(String title, int min, int max) {
        while (true) {
            int choice = readInt(title);
            if (choice >= min && choice <= max) {
                return choice;
            }
            printError("شماره گزینه باید بین " + min + " و " + max + " باشد.");
        }
    }
}
