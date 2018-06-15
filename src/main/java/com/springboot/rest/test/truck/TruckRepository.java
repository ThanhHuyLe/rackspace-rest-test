package com.springboot.rest.test.truck;

import com.springboot.rest.test.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long>{
    @Query("Select a from Truck a where a.brandName = :brandName")
    public Optional<Truck> findByBrandName(@Param("brandName") String brandName);

    @Query("Select a from Truck a where a.brandName = :brandName AND a.id = :id")
    public Optional<Truck> findByIdAndBrandName(@Param("id") String id, @Param("brandName") String brandName);
}
