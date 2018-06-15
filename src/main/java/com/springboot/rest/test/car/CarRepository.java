package com.springboot.rest.test.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
    @Query("Select a from Car a where a.brandName = :brandName")
    public Optional<Car> findByBrandName(@Param("brandName") String brandName);

    @Query("Select a from Car a where a.brandName = :brandName AND a.id = :id")
    public Optional<Car> findByIdAndBrandName(@Param("id") String id, @Param("brandName") String brandName);

}
