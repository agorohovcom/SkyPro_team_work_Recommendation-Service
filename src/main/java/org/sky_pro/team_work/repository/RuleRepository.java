package org.sky_pro.team_work.repository;


import org.sky_pro.team_work.domain.Rule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RuleRepository extends JpaRepository<Rule,Long> {

}
