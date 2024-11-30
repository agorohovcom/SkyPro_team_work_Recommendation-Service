package org.sky_pro.team_work.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class RuleStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int count;

    @OneToOne
    @JoinColumn(name = "rule_id")
    @JsonBackReference
    private Rule rule;

    public RuleStatistic(Long id, int val) {
        this.id = id;
        this.count = val;
    }
}
