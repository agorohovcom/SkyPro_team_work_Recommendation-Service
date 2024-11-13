package org.sky_pro.team_work.repository;


import org.sky_pro.team_work.domain.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RuleRepository extends JpaRepository<Rule,Long> {
    @Query(value = "SELECT * FROM rule WHERE product_id = :product_id", nativeQuery = true)
    List<Rule> getRulesByProductId(@Param("product_id") String productId);
}
