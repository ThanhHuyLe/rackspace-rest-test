package com.springboot.rest.test.boat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Long>{
    @Query("Select a from Boat a where a.brandName = :brandName")
    public Optional<Boat> findByBrandName(@Param("brandName") String brandName);

    @Query("Select a from Boat a where a.brandName = :brandName AND a.id = :id")
    public Optional<Boat> findByIdAndBrandName(@Param("id") String id, @Param("brandName") String brandName);
}
