package com.springboot.rest.test.drone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long>{
    @Query("Select a from Drone a where a.cycleperMinute = :cyclePerMinute")
    public Optional<Drone> findByCycleperMinute(@Param("cyclePerMinute") String airline);

    @Query("Select a from Drone a where a.cycleperMinute" +
            " = :cyclePerMinute AND a.id = :id")
    public Optional<Drone> findByIdAndCycleperMinute(@Param("id") String id, @Param("cyclePerMinute") String cyclePerMinute);
}
