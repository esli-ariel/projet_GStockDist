package com.esli.gestiondestock.repository;

import com.esli.gestiondestock.model.Categorieproduit;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieproduitRepository extends JpaRepository<Categorieproduit, Integer> {

  Optional<Categorieproduit> findCategorieproduitByCode(String code);

}
