package org.loveroo.uuidbruteforce;

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

        threads.forEach(Thread::interrupt);
        Runtime.getRuntime().exit(0);
    }
}
