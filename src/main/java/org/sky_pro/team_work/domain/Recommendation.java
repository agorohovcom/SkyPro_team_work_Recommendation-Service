package org.sky_pro.team_work.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommendation {
    private UUID id;
    private String name;
    private String text;
}
