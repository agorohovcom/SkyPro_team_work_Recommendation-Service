package org.sky_pro.team_work.repository;

import org.sky_pro.team_work.domain.Rule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends JpaRepository<Rule,Long> {

    @Query("SELECT DISTINCT r FROM Rule r JOIN FETCH r.query")
    List<Rule> findAllWithQueries();
}
