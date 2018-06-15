package com.springboot.rest.test.airplane;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long>{
    @Query("Select a from Airplane a where a.airline = :airline")
    public Optional<Airplane> findByAirline(@Param("airline") String airline);

    @Query("Select a from Airplane a where a.airline = :airline AND a.id = :id")
    public Optional<Airplane> findByIdAndAirline(@Param("id") String id, @Param("airline") String airline);
}
