package org.sky_pro.team_work.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Имя продукта не может быть пустым")
    private String productName;

    @JsonProperty("product_id")
    @NotBlank(message = "Id продукта не может быть пустым")
    private String productId;

    @JsonProperty("product_text")
    @NotBlank(message = "Текст продукта не может быть пустым")
    private String productText;

    @JsonProperty("rule")
    @NotNull(message = "Значение не может отсутствовать")
    @Size(min = 1, message = "Список не может быть пустым")
    @Valid
    private List<Query> query;
}
