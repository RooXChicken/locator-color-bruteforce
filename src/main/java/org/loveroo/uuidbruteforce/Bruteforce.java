package org.loveroo.uuidbruteforce;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.UUID;

public class Bruteforce extends Thread {
    private final int target;

    public Bruteforce(int target) {
        this.target = target;
    }

    @Override
    public void run() {
        while(!interrupted()) {
            final var id = UUID.randomUUID();
            final var hash = id.hashCode() & 0x00FFFFFF;

            if(hash == target) {
                System.out.println(id + " has match");

                try {
                    final var url = new URI("https://playerdb.co/api/player/minecraft/" + id).toURL();
                    final var http = (HttpURLConnection) url.openConnection();

                    final var response = new String(http.getInputStream().readAllBytes());
                    http.disconnect();

                    System.out.println(response);

                    if(response.startsWith("{\"code\":\"player.found\"")) {
                        // terrible code i'm too lazy to json
                        final var split = response.split("\"username\":\"")[1];
                        final var username = split.substring(0, split.indexOf("\""));

                        Main.playerFound(id, username);
                    }
                }
                catch(Exception e) {
//                    e.printStackTrace();
                }
            }
        }
    }
}
