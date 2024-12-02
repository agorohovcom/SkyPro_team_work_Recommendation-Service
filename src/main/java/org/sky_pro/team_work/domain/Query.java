package org.sky_pro.team_work.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.sky_pro.team_work.enums.QueryType;
import org.sky_pro.team_work.validation.ValidArgumentType;

import java.util.List;


@Setter
@Getter
@Embeddable
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Query {

 @JsonProperty("query")
 @Enumerated(EnumType.STRING)
 @NotNull(message = "Значение не может отсутствовать")
 private QueryType queryType;

 @JsonProperty("arguments")
 @NotEmpty(message = "Список аргументов не может быть пустым")
 @JdbcTypeCode(SqlTypes.JSON)
 private List<@ValidArgumentType String> arguments;

 @JsonProperty("negate")
 @NotNull(message = "Значение не может отсутствовать")
 private Boolean negate;
}
