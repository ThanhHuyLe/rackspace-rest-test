package com.springboot.rest.test.amphibian;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AmphibianRepository extends JpaRepository<Amphibian, Long>{
    @Query("Select a from Amphibian a where a.specie = :specie")
    public Optional<Amphibian> findBySpecie(@Param("specie") String specie);

    @Query("Select a from Amphibian a where a.specie = :specie AND a.id = :id")
    public Optional<Amphibian> findByIdAndSpecie(@Param("id") String id, @Param("specie") String specie);
}
