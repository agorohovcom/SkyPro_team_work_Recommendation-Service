package org.sky_pro.team_work.bot.dto;

import lombok.Data;
import org.sky_pro.team_work.domain.Recommendation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//todo Может быть перенести в пакет domain?
@Data
public class UserDto {
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private List<Recommendation> recommendations = new ArrayList<>();
}
