package com.esli.gestiondestock.repository;

import com.esli.gestiondestock.model.Commandeentrepot;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeentrepotRepository extends JpaRepository<Commandeentrepot, Integer> {

  Optional<Commandeentrepot> findCommandeentrepotByCode(String code);

  List<Commandeentrepot> findAllByentrepotId(Integer id);
}
