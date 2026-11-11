package com.netpilot.api.targets.repository;

import com.netpilot.api.targets.model.Targets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetRepository extends JpaRepository<Targets,Long> {

}

