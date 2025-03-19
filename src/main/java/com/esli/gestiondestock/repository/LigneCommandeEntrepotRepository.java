package com.esli.gestiondestock.repository;


import java.util.List;

import com.esli.gestiondestock.model.LigneCommandeEntrepot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeEntrepotRepository extends JpaRepository<LigneCommandeEntrepot, Integer> {


  List<LigneCommandeEntrepot> findAllByCommandeEntrepotId(Integer id);

  List<LigneCommandeEntrepot> findAllByProduitId(Integer id);
}
