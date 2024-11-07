package org.sky_pro.team_work.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Recommendation {

    private final String id;
    private final String name;
    private final String text;

}