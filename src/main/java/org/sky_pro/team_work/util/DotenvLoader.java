package org.sky_pro.team_work.util;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvLoader {

    public static void loadEnvironmentVariables() {
        String envFileName = ".env";

        Dotenv dotenv = Dotenv.configure()
                .filename(envFileName)
                .ignoreIfMissing()
                .ignoreIfMalformed()
                .load();

        dotenv.entries().forEach(entry -> {
            String key = entry.getKey();
            String value = entry.getValue();
            System.setProperty(key, value);
        });
    }
}
