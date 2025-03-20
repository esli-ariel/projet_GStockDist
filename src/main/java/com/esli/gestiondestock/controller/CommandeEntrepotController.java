package com.esli.gestiondestock.controller;

import com.esli.gestiondestock.controller.api.CommandeEntrepotApi;
import com.esli.gestiondestock.dto.CommandeEntrepotDto;
import com.esli.gestiondestock.dto.LigneCommandeEntrepotDto;
import com.esli.gestiondestock.model.EtatCommande;
import com.esli.gestiondestock.services.CommandeEntrepotService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandeEntrepotController implements CommandeEntrepotApi {

  private CommandeEntrepotService commandeEntrepotService;

  @Autowired
  public CommandeEntrepotController(CommandeEntrepotService commandeEntrepotService) {
    this.commandeEntrepotService = commandeEntrepotService;
  }

  @Override
  public ResponseEntity<CommandeEntrepotDto> save(CommandeEntrepotDto dto) {
    return ResponseEntity.ok(commandeEntrepotService.save(dto));
  }

  @Override
  public ResponseEntity<CommandeEntrepotDto> updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
    return ResponseEntity.ok(commandeEntrepotService.updateEtatCommande(idCommande, etatCommande));
  }

  @Override
  public ResponseEntity<CommandeEntrepotDto> updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
    return ResponseEntity.ok(commandeEntrepotService.updateQuantiteCommande(idCommande, idLigneCommande, quantite));
  }

  @Override
  public ResponseEntity<CommandeEntrepotDto> updateEntrepot(Integer idCommande, Integer idEntrepot) {
    return ResponseEntity.ok(commandeEntrepotService.updateEntrepot(idCommande, idEntrepot));
  }

  @Override
  public ResponseEntity<CommandeEntrepotDto> updateProduit(Integer idCommande, Integer idLigneCommande, Integer idProduit) {
    return ResponseEntity.ok(commandeEntrepotService.updateProduit(idCommande, idLigneCommande, idProduit));
  }

  @Override
  public ResponseEntity<CommandeEntrepotDto> deleteProduit(Integer idCommande, Integer idLigneCommande) {
    return ResponseEntity.ok(commandeEntrepotService.deleteProduit(idCommande, idLigneCommande));
  }

  @Override
  public ResponseEntity<CommandeEntrepotDto> findById(Integer id) {
    return ResponseEntity.ok(commandeEntrepotService.findById(id));
  }

  @Override
  public ResponseEntity<CommandeEntrepotDto> findByCode(String code) {
    return ResponseEntity.ok(commandeEntrepotService.findByCode(code));
  }

  @Override
  public ResponseEntity<List<CommandeEntrepotDto>> findAll() {
    return ResponseEntity.ok(commandeEntrepotService.findAll());
  }

  @Override
  public ResponseEntity<List<LigneCommandeEntrepotDto>> findAllLignesCommandesEntrepotByCommandeEntrepotId(Integer idCommande) {
    return ResponseEntity.ok(commandeEntrepotService.findAllLignesCommandesEntrepotByCommandeEntrepotId(idCommande));
  }

  @Override
  public ResponseEntity<Void> delete(Integer id) {
    commandeEntrepotService.delete(id);
    return ResponseEntity.ok().build();
  }
}
