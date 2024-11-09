package org.example.parcial_2.Normal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ValorNormalRepository extends JpaRepository<ValorNormal, Long> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE valor_normal", nativeQuery = true)
    void truncateTable();
}