package com.esli.gestiondestock.controller.api;

import static com.esli.gestiondestock.utils.Constants.APP_ROOT;

import com.esli.gestiondestock.dto.CommandeEntrepotDto;
import com.esli.gestiondestock.dto.LigneCommandeEntrepotDto;
import com.esli.gestiondestock.model.EtatCommande;
import io.swagger.annotations.Api;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api("commandesEntrepots")
public interface CommandeEntrepotApi {


  @PostMapping(APP_ROOT + "/commandesentrepots/create")
  ResponseEntity<CommandeEntrepotDto> save(@RequestBody CommandeEntrepotDto dto);

  @PatchMapping(APP_ROOT + "/commandesentrepots/update/etat/{idCommande}/{etatCommande}")
  ResponseEntity<CommandeEntrepotDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

  @PatchMapping(APP_ROOT + "/commandesentrepots/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
  ResponseEntity<CommandeEntrepotDto> updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,
      @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);

  @PatchMapping(APP_ROOT + "/commandesentrepots/update/entrepot/{idCommande}/{idEntrepot}")
  ResponseEntity<CommandeEntrepotDto> updateEntrepot(@PathVariable("idCommande") Integer idCommande, @PathVariable("idEntrepot") Integer idEntrepot);

  @PatchMapping(APP_ROOT + "/commandesentrepots/update/produit/{idCommande}/{idLigneCommande}/{idProduit}")
  ResponseEntity<CommandeEntrepotDto> updateProduit(@PathVariable("idCommande") Integer idCommande,
      @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idProduit") Integer idProduit);

  @DeleteMapping(APP_ROOT + "/commandesentrepots/delete/produit/{idCommande}/{idLigneCommande}")
  ResponseEntity<CommandeEntrepotDto> deleteProduit(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

  @GetMapping(APP_ROOT + "/commandesentrepots/{idCommandeEntrepot}")
  ResponseEntity<CommandeEntrepotDto> findById(@PathVariable Integer idCommandeEntrepot);

  @GetMapping(APP_ROOT + "/commandesentrepots/filter/{codeCommandeEntrepot}")
  ResponseEntity<CommandeEntrepotDto> findByCode(@PathVariable("codeCommandeEntrepot") String code);

  @GetMapping(APP_ROOT + "/commandesentrepots/all")
  ResponseEntity<List<CommandeEntrepotDto>> findAll();

  @GetMapping(APP_ROOT + "/commandesentrepots/lignesCommande/{idCommande}")
  ResponseEntity<List<LigneCommandeEntrepotDto>> findAllLignesCommandesEntrepotByCommandeEntrepotId(@PathVariable("idCommande") Integer idCommande);

  @DeleteMapping(APP_ROOT + "/commandesentrepots/delete/{idCommandeEntrepot}")
  ResponseEntity<Void> delete(@PathVariable("idCommandeEntrepot") Integer id);

}
