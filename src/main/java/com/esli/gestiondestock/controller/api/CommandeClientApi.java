package com.esli.gestiondestock.controller.api;

import static com.bouali.gestiondestock.utils.Constants.APP_ROOT;

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


  @PostMapping(APP_ROOT + "/commandesclients/create")
  ResponseEntity<CommandeEntrepotDto> save(@RequestBody CommandeEntrepotDto dto);

  @PatchMapping(APP_ROOT + "/commandesclients/update/etat/{idCommande}/{etatCommande}")
  ResponseEntity<CommandeEntrepotDto> updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

  @PatchMapping(APP_ROOT + "/commandesclients/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
  ResponseEntity<CommandeEntrepotDto> updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,
      @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);

  @PatchMapping(APP_ROOT + "/commandesclients/update/client/{idCommande}/{idClient}")
  ResponseEntity<CommandeEntrepotDto> updateEntrepot(@PathVariable("idCommande") Integer idCommande, @PathVariable("idEntrepot") Integer idEntrepot);

  @PatchMapping(APP_ROOT + "/commandesclients/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
  ResponseEntity<CommandeEntrepotDto> updateArticle(@PathVariable("idCommande") Integer idCommande,
      @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

  @DeleteMapping(APP_ROOT + "/commandesclients/delete/article/{idCommande}/{idLigneCommande}")
  ResponseEntity<CommandeEntrepotDto> deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

  @GetMapping(APP_ROOT + "/commandesclients/{idCommandeClient}")
  ResponseEntity<CommandeEntrepotDto> findById(@PathVariable Integer idCommandeClient);

  @GetMapping(APP_ROOT + "/commandesclients/filter/{codeCommandeClient}")
  ResponseEntity<CommandeEntrepotDto> findByCode(@PathVariable("codeCommandeClient") String code);

  @GetMapping(APP_ROOT + "/commandesclients/all")
  ResponseEntity<List<CommandeEntrepotDto>> findAll();

  @GetMapping(APP_ROOT + "/commandesclients/lignesCommande/{idCommande}")
  ResponseEntity<List<LigneCommandeEntrepotDto>> findAllLignesCommandesEntrepotByCommandeEntrepotId(@PathVariable("idCommande") Integer idCommande);

  @DeleteMapping(APP_ROOT + "/commandesclients/delete/{idCommandeEntrepot}")
  ResponseEntity<Void> delete(@PathVariable("idCommandeEntrepot") Integer id);

}
