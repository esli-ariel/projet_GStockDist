package com.esli.gestiondestock.repository;

import com.esli.gestiondestock.model.Produit;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {

  Optional<Produit> findProduitByCodeProduit(String codeProduit);

  List<Produit> findAllByCategorieproduitId(Integer idCategorieproduit);


}
