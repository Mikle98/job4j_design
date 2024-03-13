package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> dialogArhive = new ArrayList<>();
        List<String> answerBot = readPhrases();
        System.out.println("Enter text");
        String userText = scanner.nextLine();
        dialogArhive.add(userText);
        while (!OUT.equals(userText)) {
            if (dialogArhive.lastIndexOf(STOP) <= dialogArhive.lastIndexOf(CONTINUE)) {
                int index = new Random().nextInt(0, answerBot.size());
                System.out.println(answerBot.get(index));
                dialogArhive.add(answerBot.get(index));
            }
            userText = scanner.nextLine();
            dialogArhive.add(userText);
        }
        saveLog(dialogArhive);
    }

    private List<String> readPhrases() {
        List<String> answer = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.botAnswers,
                                                            Charset.forName("WINDOWS-1251")))) {
            reader.lines().forEach(answer::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path,
                                                            Charset.forName("WINDOWS-1251"),
                                                     true))) {
            for (String str : log) {
                writer.write(String.format("%s%n", str));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat consoleChat = new ConsoleChat("./data/answerBotLog.txt", "./data/answerBot.txt");
        consoleChat.run();
    }
}
