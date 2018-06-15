package com.springboot.rest.test.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
    @Query(nativeQuery = true,
            value = "select * from Vehicle where timestamp = (select max(timestamp) from Vehicle) order by ID DESC limit 1")
    public Vehicle findLatest();
}
