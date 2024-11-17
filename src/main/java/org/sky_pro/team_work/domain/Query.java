package org.sky_pro.team_work.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.sky_pro.team_work.enums.QueryType;

import java.util.List;

@Setter
@Getter
@Embeddable
@ToString
public class Query {

 @JsonProperty("query")
 @Enumerated(EnumType.STRING)
 private QueryType queryType;

 @JsonProperty("arguments")
 private List<String> arguments;

 @JsonProperty("negate")
 private Boolean negate;
}
