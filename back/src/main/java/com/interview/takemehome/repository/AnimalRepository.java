package com.interview.takemehome.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.interview.takemehome.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, UUID> {
}
