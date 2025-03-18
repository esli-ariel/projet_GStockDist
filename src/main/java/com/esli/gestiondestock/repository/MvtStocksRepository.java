package com.esli.gestiondestock.repository;

import com.esli.gestiondestock.model.MvtStocks;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MvtStocksRepository extends JpaRepository<MvtStocks, Integer> {

  @Query("select sum(m.quantite) from MvtStk m where m.produit.id = :idProduit")
  BigDecimal stockReelProduit(@Param("idProduit") Integer idProduit);

  List<MvtStocks> findAllByProduitId(Integer idProduit);

}
