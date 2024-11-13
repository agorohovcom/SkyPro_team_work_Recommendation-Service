package org.sky_pro.team_work.domain;

import java.util.UUID;

public abstract class Recommendation implements RecommendationRuleSet {
    protected UUID id;
    protected String name;
    protected String text;
}
