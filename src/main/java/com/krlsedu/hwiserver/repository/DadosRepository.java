package com.krlsedu.hwiserver.repository;

import com.krlsedu.hwiserver.model.Dados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosRepository extends JpaRepository<Dados, Long> {
}
