package com.esli.gestiondestock.services.impl;

import com.esli.gestiondestock.dto.ProduitDto;
import com.esli.gestiondestock.dto.LigneCommandeEntrepotDto;
import com.esli.gestiondestock.dto.LigneCommandeFournisseurDto;
//import com.esli.gestiondestock.dto.LigneVenteDto;
import com.esli.gestiondestock.exception.EntityNotFoundException;
import com.esli.gestiondestock.exception.ErrorCodes;
import com.esli.gestiondestock.exception.InvalidEntityException;
import com.esli.gestiondestock.exception.InvalidOperationException;
import com.esli.gestiondestock.model.LigneCommandeEntrepot;
import com.esli.gestiondestock.model.LigneCommandeFournisseur;
//import com.esli.gestiondestock.model.LigneVente;
import com.esli.gestiondestock.repository.ProduitRepository;
import com.esli.gestiondestock.repository.LigneCommandeEntrepotRepository;
import com.esli.gestiondestock.repository.LigneCommandeFournisseurRepository;
//import com.esli.gestiondestock.repository.LigneVenteRepository;
import com.esli.gestiondestock.services.ProduitService;
import com.esli.gestiondestock.validator.ProduitValidator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class ProduitServiceImpl implements ProduitService {

  private ProduitRepository produitRepository;
  //private LigneVenteRepository venteRepository;
  private LigneCommandeFournisseurRepository commandeFournisseurRepository;
  private LigneCommandeEntrepotRepository commandeEntrepotRepository;

  @Autowired
  public ProduitServiceImpl(
      ProduitRepository produitRepository,
     // LigneVenteRepository venteRepository
           LigneCommandeFournisseurRepository commandeFournisseurRepository,
      LigneCommandeEntrepotRepository commandeEntrepotRepository) {
    this.produitRepository = produitRepository;
   // this.venteRepository = venteRepository;
    this.commandeFournisseurRepository = commandeFournisseurRepository;
    this.commandeEntrepotRepository = commandeEntrepotRepository;
  }

  @Override
  public ProduitDto save(ProduitDto dto) {
    List<String> errors = ProduitValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Produit is not valid {}", dto);
      throw new InvalidEntityException("L'produit n'est pas valide", ErrorCodes.PRODUIT_NOT_VALID, errors);
    }

    return ProduitDto.fromEntity(
        produitRepository.save(
            ProduitDto.toEntity(dto)
        )
    );
  }

  @Override
  public ProduitDto findById(Integer id) {
    if (id == null) {
      log.error("Produit ID is null");
      return null;
    }

    return produitRepository.findById(id).map(ProduitDto::fromEntity).orElseThrow(() ->
        new EntityNotFoundException(
            "Aucun produit avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.PRODUIT_NOT_FOUND)
    );
  }

  @Override
  public ProduitDto findByCodeProduit(String codeProduit) {
    if (!StringUtils.hasLength(codeProduit)) {
      log.error("Produit CODE is null");
      return null;
    }

    return produitRepository.findProduitByCodeProduit(codeProduit)
        .map(ProduitDto::fromEntity)
        .orElseThrow(() ->
            new EntityNotFoundException(
                "Aucun produit avec le CODE = " + codeProduit + " n' ete trouve dans la BDD",
                ErrorCodes.PRODUIT_NOT_FOUND)
        );
  }

  @Override
  public List<ProduitDto> findAll() {
    return produitRepository.findAll().stream()
        .map(ProduitDto::fromEntity)
        .collect(Collectors.toList());
  }

 // @Override
//  public List<LigneVenteDto> findHistoriqueVentes(Integer idProduit) {
//    return venteRepository.findAllByProduitId(idProduit).stream()
 //       .map(LigneVenteDto::fromEntity)
//        .collect(Collectors.toList());
//  }

  @Override
  public List<LigneCommandeEntrepotDto> findHistoriaueCommandeEntrepot(Integer idProduit) {
    return commandeEntrepotRepository.findAllByProduitId(idProduit).stream()
        .map(LigneCommandeEntrepotDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idProduit) {
    return commandeFournisseurRepository.findAllByProduitId(idProduit).stream()
        .map(LigneCommandeFournisseurDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public List<ProduitDto> findAllProduitByIdCategorieproduit(Integer idCategorieproduit) {
    return produitRepository.findAllByCategorieproduitId(idCategorieproduit).stream()
        .map(ProduitDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Produit ID is null");
      return;
    }
    List<LigneCommandeEntrepot> ligneCommandeEntrepots = commandeEntrepotRepository.findAllByProduitId(id);
    if (!ligneCommandeEntrepots.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un produit deja utilise dans des commandes client", ErrorCodes.PRODUIT_ALREADY_IN_USE);
    }
    List<LigneCommandeFournisseur> ligneCommandeFournisseurs = commandeFournisseurRepository.findAllByProduitId(id);
    if (!ligneCommandeFournisseurs.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un produit deja utilise dans des commandes fournisseur",
          ErrorCodes.PRODUIT_ALREADY_IN_USE);
    }
   // List<LigneVente> ligneVentes = venteRepository.findAllByProduitId(id);
   // if (!ligneVentes.isEmpty()) {
   //   throw new InvalidOperationException("Impossible de supprimer un produit deja utilise dans des ventes",
    //      ErrorCodes.Produit_ALREADY_IN_USE);
  //  }
    produitRepository.deleteById(id);
  }
}
