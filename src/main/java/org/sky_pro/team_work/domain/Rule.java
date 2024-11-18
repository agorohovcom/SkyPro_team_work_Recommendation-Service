package org.sky_pro.team_work.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Setter
@Getter
@Entity
@ToString
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("product_text")
    private String productText;

    @ElementCollection
    @CollectionTable(name = "query", joinColumns = @JoinColumn(name = "rule_id"))
    @JsonProperty("rule")
    private List<Query> query;

    @OneToOne(mappedBy = "rule", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private RuleStatistic ruleStatistic;
}
