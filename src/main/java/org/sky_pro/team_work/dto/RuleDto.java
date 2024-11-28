package org.sky_pro.team_work.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.sky_pro.team_work.domain.Query;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RuleDto {
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("product_text")
    private String productText;
    @JsonProperty("rule")
    private List<Query> query;
}
