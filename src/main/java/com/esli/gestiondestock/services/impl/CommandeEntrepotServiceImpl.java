package com.esli.gestiondestock.services.impl;

import com.esli.gestiondestock.dto.*;
import com.esli.gestiondestock.exception.EntityNotFoundException;
import com.esli.gestiondestock.exception.ErrorCodes;
import com.esli.gestiondestock.exception.InvalidEntityException;
import com.esli.gestiondestock.exception.InvalidOperationException;
import com.esli.gestiondestock.model.*;
import com.esli.gestiondestock.repository.*;
import com.esli.gestiondestock.services.CommandeEntrepotService;
import com.esli.gestiondestock.services.MvtStocksService;
import com.esli.gestiondestock.validator.ProduitValidator;
import com.esli.gestiondestock.validator.CommandeEntrepotValidator;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class CommandeEntrepotServiceImpl implements CommandeEntrepotService {

  private CommandeEntrepotRepository commandeEntrepotRepository;
  private LigneCommandeEntrepotRepository ligneCommandeEntrepotRepository;
  private EntrepotRepository entrepotRepository;
  private ProduitRepository produitRepository;
  private MvtStocksService mvtStocksService;
  private CommandeFournisseurRepository commandeFournisseurRepository;
  private FournisseurRepository fournisseurRepository;


  @Autowired
  public CommandeEntrepotServiceImpl(CommandeEntrepotRepository commandeEntrepotRepository,
      EntrepotRepository entrepotRepository, ProduitRepository produitRepository, LigneCommandeEntrepotRepository ligneCommandeEntrepotRepository,
      MvtStocksService mvtStocksService) {
    this.commandeEntrepotRepository = commandeEntrepotRepository;
    this.ligneCommandeEntrepotRepository = ligneCommandeEntrepotRepository;
    this.entrepotRepository = entrepotRepository;
    this.produitRepository = produitRepository;
    this.mvtStocksService = mvtStocksService;
    this.commandeFournisseurRepository = commandeFournisseurRepository;
    this.fournisseurRepository = fournisseurRepository;
  }

  @Override
  public CommandeEntrepotDto save(CommandeEntrepotDto dto) {

    List<String> errors = CommandeEntrepotValidator.validate(dto);

    if (!errors.isEmpty()) {
      log.error("Commande Entrepot n'est pas valide");
      throw new InvalidEntityException("La commande entrepot n'est pas valide", ErrorCodes.COMMANDE_ENTREPOT_NOT_VALID, errors);
    }

    if (dto.getId() != null && dto.isCommandeLivree()) {
      throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_ENTREPOT_NON_MODIFIABLE);
    }

    Optional<Entrepot> entrepot = entrepotRepository.findById(dto.getEntrepot().getId());
    if (entrepot.isEmpty()) {
      log.warn("Client with ID {} was not found in the DB", dto.getEntrepot().getId());
      throw new EntityNotFoundException("Aucun client avec l'ID" + dto.getEntrepot().getId() + " n'a ete trouve dans la BDD",
          ErrorCodes.ENTREPOT_NOT_FOUND);
    }

    List<String> produitErrors = new ArrayList<>();

//    if (dto.getLigneCommandeEntrepots() != null) {
//      dto.getLigneCommandeEntrepots().forEach(ligCmdClt -> {
//        if (ligCmdClt.getProduit() != null) {
//          Optional<Produit> produit = produitRepository.findById(ligCmdClt.getProduit().getId());
//          if (produit.isEmpty()) {
//            produitErrors.add("L'produit avec l'ID " + ligCmdClt.getProduit().getId() + " n'existe pas");
//          }
//        } else {
//          produitErrors.add("Impossible d'enregister une commande avec un aticle NULL");
//        }
//      });
//    }

    if (!produitErrors.isEmpty()) {
      log.warn("");
      throw new InvalidEntityException("Produit n'existe pas dans la BDD", ErrorCodes.PRODUIT_NOT_FOUND, produitErrors);
    }
    dto.setDateCommande(Instant.now());
    CommandeEntrepot savedCmdClt = commandeEntrepotRepository.save(CommandeEntrepotDto.toEntity(dto));

//    if (dto.getLigneCommandeEntrepots() != null) {
//      dto.getLigneCommandeEntrepots().forEach(ligCmdClt -> {
//        LigneCommandeEntrepot ligneCommandeEntrepot = LigneCommandeEntrepotDto.toEntity(ligCmdClt);
//        ligneCommandeEntrepot.setCommandeEntrepot(savedCmdClt);
//        LigneCommandeEntrepot savedLigneCmd = ligneCommandeEntrepotRepository.save(ligneCommandeEntrepot);
//
//        effectuerSortie(savedLigneCmd);
//      });
//    }

    return CommandeEntrepotDto.fromEntity(savedCmdClt);
  }

  @Override
  public CommandeEntrepotDto findById(Integer id) {
    if (id == null) {
      log.error("Commande entrepot ID is NULL");
      return null;
    }
    return commandeEntrepotRepository.findById(id)
        .map(CommandeEntrepotDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucune commande entrepot n'a ete trouve avec l'ID " + id, ErrorCodes.COMMANDE_ENTREPOT_NOT_FOUND
        ));
  }

  @Override
  public CommandeEntrepotDto findByCode(String code) {
    if (!StringUtils.hasLength(code)) {
      log.error("Commande entrepot CODE is NULL");
      return null;
    }
    return commandeEntrepotRepository.findCommandeEntrepotByCode(code)
        .map(CommandeEntrepotDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucune commande entrepot n'a ete trouve avec le CODE " + code, ErrorCodes.COMMANDE_ENTREPOT_NOT_FOUND
        ));
  }

  @Override
  public List<CommandeEntrepotDto> findAll() {
    return commandeEntrepotRepository.findAll().stream()
        .map(CommandeEntrepotDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Commande entrepot ID is NULL");
      return;
    }
    List<LigneCommandeEntrepot> ligneCommandeEntrepots = ligneCommandeEntrepotRepository.findAllByCommandeEntrepotId(id);
    if (!ligneCommandeEntrepots.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer une commande entrepot deja utilisee",
          ErrorCodes.COMMANDE_ENTREPOT_ALREADY_IN_USE);
    }
    commandeEntrepotRepository.deleteById(id);
  }

  @Override
  public List<LigneCommandeEntrepotDto> findAllLignesCommandesEntrepotByCommandeEntrepotId(Integer idCommande) {
    return ligneCommandeEntrepotRepository.findAllByCommandeEntrepotId(idCommande).stream()
        .map(LigneCommandeEntrepotDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public CommandeEntrepotDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
    checkIdCommande(idCommande);
    if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
      log.error("L'etat de la commande entrepot is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null",
          ErrorCodes.COMMANDE_ENTREPOT_NON_MODIFIABLE);
    }
    CommandeEntrepotDto commandeEntrepot = checkEtatCommande(idCommande);
    commandeEntrepot.setEtatCommande(etatCommande);

    CommandeEntrepot savedCmdClt = commandeEntrepotRepository.save(CommandeEntrepotDto.toEntity(commandeEntrepot));
    if (commandeEntrepot.isCommandeLivree()) {
      updateMvtStocks(idCommande);
    }

    return CommandeEntrepotDto.fromEntity(savedCmdClt);
  }

  @Override
  public CommandeEntrepotDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);

    if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0) {
      log.error("L'ID de la ligne commande is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une quantite null ou ZERO",
          ErrorCodes.COMMANDE_ENTREPOT_NON_MODIFIABLE);
    }

    CommandeEntrepotDto commandeEntrepot = checkEtatCommande(idCommande);
    Optional<LigneCommandeEntrepot> ligneCommandeEntrepotOptional = findLigneCommandeEntrepot(idLigneCommande);

    LigneCommandeEntrepot ligneCommandeEntrepot = ligneCommandeEntrepotOptional.get();
    ligneCommandeEntrepot.setQuantite(quantite);
    ligneCommandeEntrepotRepository.save(ligneCommandeEntrepot);

    return commandeEntrepot;
  }

  @Override
  public CommandeEntrepotDto updateentrepot(Integer idCommande, Integer idEntrepot) {
    return null;
  }

  @Override
  public CommandeEntrepotDto updateEntrepot(Integer idCommande, Integer idEntrepot) {
    checkIdCommande(idCommande);
    if (idEntrepot == null) {
      log.error("L'ID du entrepot is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID entrepot null",
          ErrorCodes.COMMANDE_ENTREPOT_NON_MODIFIABLE);
    }
    CommandeEntrepotDto commandeEntrepot = checkEtatCommande(idCommande);
    Optional<Entrepot> EntrepotOptional = entrepotRepository.findById(idEntrepot);
    if (EntrepotOptional.isEmpty()) {
      throw new EntityNotFoundException(
          "Aucun entrepot n'a ete trouve avec l'ID " + idEntrepot, ErrorCodes.ENTREPOT_NOT_FOUND);
    }
    commandeEntrepot.setEntrepot(EntrepotDto.fromEntity(EntrepotOptional.get()));

    return CommandeEntrepotDto.fromEntity(
        commandeEntrepotRepository.save(CommandeEntrepotDto.toEntity(commandeEntrepot))
    );
  }

  @Override
  public CommandeEntrepotDto updateProduit(Integer idCommande, Integer idLigneCommande, Integer idProduit) {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);
    checkIdProduit(idProduit, "nouvel");

    CommandeEntrepotDto commandeEntrepot = checkEtatCommande(idCommande);

    Optional<LigneCommandeEntrepot> ligneCommandeEntrepot = findLigneCommandeEntrepot(idLigneCommande);

    Optional<Produit> produitOptional = produitRepository.findById(idProduit);
    if (produitOptional.isEmpty()) {
      throw new EntityNotFoundException(
          "Aucune produit n'a ete trouve avec l'ID " + idProduit, ErrorCodes.PRODUIT_NOT_FOUND);
    }

    List<String> errors = ProduitValidator.validate(ProduitDto.fromEntity(produitOptional.get()));
    if (!errors.isEmpty()) {
      throw new InvalidEntityException("Produit invalid", ErrorCodes.PRODUIT_NOT_VALID, errors);
    }

    LigneCommandeEntrepot ligneCommandeEntrepotToSaved = ligneCommandeEntrepot.get();
    ligneCommandeEntrepotToSaved.setProduit(produitOptional.get());
    ligneCommandeEntrepotRepository.save(ligneCommandeEntrepotToSaved);

    return commandeEntrepot;
  }

  @Override
  public CommandeEntrepotDto deleteProduit(Integer idCommande, Integer idLigneCommande) {
    checkIdCommande(idCommande);
    checkIdLigneCommande(idLigneCommande);

    CommandeEntrepotDto commandeEntrepot = checkEtatCommande(idCommande);
    // Just to check the LigneCommandeEntrepot and inform the entrepot in case it is absent
    findLigneCommandeEntrepot(idLigneCommande);
    ligneCommandeEntrepotRepository.deleteById(idLigneCommande);

    return commandeEntrepot;
  }

  private CommandeEntrepotDto checkEtatCommande(Integer idCommande) {
    CommandeEntrepotDto commandeEntrepot = findById(idCommande);
    if (commandeEntrepot.isCommandeLivree()) {
      throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livree", ErrorCodes.COMMANDE_ENTREPOT_NON_MODIFIABLE);
    }
    return commandeEntrepot;
  }

  private Optional<LigneCommandeEntrepot> findLigneCommandeEntrepot(Integer idLigneCommande) {
    Optional<LigneCommandeEntrepot> ligneCommandeEntrepotOptional = ligneCommandeEntrepotRepository.findById(idLigneCommande);
    if (ligneCommandeEntrepotOptional.isEmpty()) {
      throw new EntityNotFoundException(
          "Aucune ligne commande entrepot n'a ete trouve avec l'ID " + idLigneCommande, ErrorCodes.COMMANDE_ENTREPOT_NOT_FOUND);
    }
    return ligneCommandeEntrepotOptional;
  }

  private void checkIdCommande(Integer idCommande) {
    if (idCommande == null) {
      log.error("Commande entrepot ID is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID null",
          ErrorCodes.COMMANDE_ENTREPOT_NON_MODIFIABLE);
    }
  }

  private void checkIdLigneCommande(Integer idLigneCommande) {
    if (idLigneCommande == null) {
      log.error("L'ID de la ligne commande is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une ligne de commande null",
          ErrorCodes.COMMANDE_ENTREPOT_NON_MODIFIABLE);
    }
  }

  private void checkIdProduit(Integer idProduit, String msg) {
    if (idProduit == null) {
      log.error("L'ID de " + msg + " is NULL");
      throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un " + msg + " ID produit null",
          ErrorCodes.COMMANDE_ENTREPOT_NON_MODIFIABLE);
    }
  }

  private void updateMvtStocks(Integer idCommande) {
    List<LigneCommandeEntrepot> ligneCommandeEntrepots = ligneCommandeEntrepotRepository.findAllByCommandeEntrepotId(idCommande);
    ligneCommandeEntrepots.forEach(lig -> {
      effectuerSortie(lig);
    });
  }

  private void effectuerSortie(LigneCommandeEntrepot lig) {
    MvtStocksDto mvtStocksDto = MvtStocksDto.builder()
        .produit(ProduitDto.fromEntity(lig.getProduit()))
        .dateMvt(Instant.now())
        .typeMvt(TypeMvtStock.SORTIE)
        .sourceMvt(SrcMvtStock.COMMANDE_ENTREPOT)
        .quantite(lig.getQuantite())
        .build();
    mvtStocksService.sortieStock(mvtStocksDto);
  }
  @Override
  public CommandeFournisseurDto passerCommandeFournisseur(Integer idEntrepot, Integer idFournisseur, List<LigneCommandeFournisseurDto> lignesCommande) {
    if (idEntrepot == null || idFournisseur == null) {
      throw new InvalidOperationException("Entrepôt ou fournisseur ID est null", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID);
    }

    // Vérifier que l'entrepôt existe
    Optional<Entrepot> entrepotOpt = entrepotRepository.findById(idEntrepot);
    if (entrepotOpt.isEmpty()) {
      throw new EntityNotFoundException("Entrepôt non trouvé avec ID: " + idEntrepot, ErrorCodes.ENTREPOT_NOT_FOUND);
    }

    // Vérifier que le fournisseur existe
    Optional<Fournisseur> fournisseurOpt = fournisseurRepository.findById(idFournisseur);
    if (fournisseurOpt.isEmpty()) {
      throw new EntityNotFoundException("Fournisseur non trouvé avec ID: " + idFournisseur, ErrorCodes.FOURNISSEUR_NOT_FOUND);
    }

    // Créer la commande fournisseur
    CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
    commandeFournisseur.setFournisseur(fournisseurOpt.get());
    commandeFournisseur.setDateCommande(Instant.now());

    CommandeFournisseur savedCommande = commandeFournisseurRepository.save(commandeFournisseur);

    // Ajouter les lignes de commande
    List<LigneCommandeFournisseur> ligneCommandes = new ArrayList<>();
    for (LigneCommandeFournisseurDto ligneDto : lignesCommande) {
      Optional<Produit> produitOpt = produitRepository.findById(ligneDto.getProduit().getId());
      if (produitOpt.isEmpty()) {
        throw new EntityNotFoundException("Produit non trouvé avec ID: " + ligneDto.getProduit().getId(), ErrorCodes.PRODUIT_NOT_FOUND);
      }

      LigneCommandeFournisseur ligneCommande = new LigneCommandeFournisseur();
      ligneCommande.setCommandeFournisseur(savedCommande);
      ligneCommande.setProduit(produitOpt.get());
      ligneCommande.setQuantite(ligneDto.getQuantite());

      ligneCommandes.add(ligneCommande);
    }

    commandeFournisseurRepository.save(savedCommande);

    return CommandeFournisseurDto.fromEntity(savedCommande);
  }

}
