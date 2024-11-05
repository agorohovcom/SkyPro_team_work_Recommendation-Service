package org.sky_pro.team_work.recommendation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class Recommendation implements RecommendationRuleSet {
    protected String name;
    protected UUID id;
    protected String text;
}
