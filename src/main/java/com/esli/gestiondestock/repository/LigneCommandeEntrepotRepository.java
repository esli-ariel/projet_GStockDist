package com.esli.gestiondestock.repository;


import java.util.List;

import com.esli.gestiondestock.model.LigneCommandeEntrepot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeEntrepotRepository extends JpaRepository<LigneCommandeEntrepot, Integer> {


  List<LigneCommandeEntrepot> findAllByCommandeClientId(Integer id);

  List<LigneCommandeEntrepot> findAllByArticleId(Integer id);
}
