package org.sky_pro.team_work;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sky_pro.team_work.util.DotenvLoader;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeamWorkApplicationTests {

    @BeforeAll
    public static void setUp() {
        // Это нужно для того, чтобы тесты не падали из-за отсутствия переменных
        DotenvLoader.loadEnvironmentVariables();
    }

    @Test
    void contextLoads() {
    }

}
