package com.esli.gestiondestock.repository;

import com.esli.gestiondestock.model.CommandeEntrepot;
import com.esli.gestiondestock.model.CommandeFournisseur;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

  Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);

  List<CommandeEntrepot> findAllByFournisseursId(Integer id);

  List<CommandeFournisseur> findAllByEntrepotId(Integer id);
  
}
