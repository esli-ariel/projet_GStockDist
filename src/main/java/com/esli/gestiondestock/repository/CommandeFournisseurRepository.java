package com.esli.gestiondestock.repository;

import com.esli.gestiondestock.model.Commandeentrepot;
import com.esli.gestiondestock.model.CommandeFournisseur;
import java.util.List;
import java.util.Optional;

import com.esli.gestiondestock.model.Commandeentrepot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

  Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);

  List<Commandeentrepot> findAllByFournisseurId(Integer id);
}
