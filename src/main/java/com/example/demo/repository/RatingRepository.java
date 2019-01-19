package com.example.demo.repository;

import com.example.demo.model.Beer;
import org.hibernate.annotations.NamedQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.security.acl.LastOwnerException;
import java.util.List;

@Repository
@Transactional
public interface RatingRepository extends CrudRepository<Beer, Long> {

    @Query("SELECT b FROM Beer b WHERE b.rating = 5 ORDER BY 10")
    List<Beer> findTopByRating();

    @Query("SELECT b.factory, count(b.factory) FROM Beer b")
    List<Beer> findFactoryFromBeer();

    @Query("SELECT b.name FROM Beer b where b.rating = 5 and b.factory=:factory ")
    List<Beer> findBestBeerByFactory(@Param("factory") String factory);

}




