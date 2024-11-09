package org.example.parcial_2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValorNormalRepository extends JpaRepository<ValorNormal, Long> {
}