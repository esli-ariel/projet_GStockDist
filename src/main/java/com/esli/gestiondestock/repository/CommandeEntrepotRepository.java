package com.esli.gestiondestock.repository;

import com.esli.gestiondestock.model.CommandeEntrepot;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeEntrepotRepository extends JpaRepository<CommandeEntrepot, Integer> {

  Optional<CommandeEntrepot> findCommandeEntrepotByCode(String code);

  List<CommandeEntrepot> findAllByEntrepotId(Integer id);
}
