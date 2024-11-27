package org.sky_pro.team_work.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sky_pro.team_work.domain.Query;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "id", "productName", "productId", "productText", "query" })
public class RuleDto {
    private Long id;
    private String productName;
    private String productId;
    private String productText;
    private List<Query> query;
}
