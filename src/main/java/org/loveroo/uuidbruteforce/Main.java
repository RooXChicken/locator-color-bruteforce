package org.loveroo.uuidbruteforce;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static final List<Bruteforce> threads = new ArrayList<>();

    public static void main(String[] args) {
        final var scanner = new Scanner(System.in);
        System.out.println("Input the target hex color (ex: FFFFFF)");
        System.out.print("> ");

        final var target = scanner.nextLine();
        final var hash = Integer.parseInt(target, 16);

        System.out.println("Searching for uuid with hash: " + target);

        final var threadCount = Runtime.getRuntime().availableProcessors();

        for(var i = 0; i < threadCount; i++) {
            final var thread = new Bruteforce(hash);
            threads.add(thread);

            thread.start();
        }
    }

    public static void playerFound(UUID id, String username) {
        System.out.println("Found player with color!");

        System.out.println(id.toString());
        System.out.println(username);

        try(final var writer = new FileOutputStream("result.txt")) {
            writer.write((id + " " + username).getBytes());
        }
        catch(Exception e) {}

        threads.forEach(Thread::interrupt);

        System.out.println("Press enter to exit...");
        final var scanner = new Scanner(System.in);
        scanner.nextLine();

        Runtime.getRuntime().exit(0);
    }
}
