package com.esli.gestiondestock.controller.api;

import static com.esli.gestiondestock.utils.Constants.APP_ROOT;

import com.esli.gestiondestock.dto.ProduitDto;
import com.esli.gestiondestock.dto.LigneCommandeEntrepotDto;
import com.esli.gestiondestock.dto.LigneCommandeFournisseurDto;
//import com.esli.gestiondestock.dto.LigneVenteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.*;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api("Produits")
public interface ProduitApi {

  @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Enregistrer un article", notes = "Cette methode permet d'enregistrer ou modifier un article", response = ProduitDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "L'objet article cree / modifie"),
      @ApiResponse(code = 400, message = "L'objet article n'est pas valide")
  })
  ProduitDto save(@RequestBody ProduitDto dto);

  @GetMapping(value = APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Rechercher un article par ID", notes = "Cette methode permet de chercher un article par son ID", response = ProduitDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "L'article a ete trouve dans la BDD"),
      @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec l'ID fourni")
  })
  ProduitDto findById(@PathVariable("idProduit") Integer id);

  @GetMapping(value = APP_ROOT + "/articles/filter/{codeProduit}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Rechercher un article par CODE", notes = "Cette methode permet de chercher un article par son CODE", response =
      ProduitDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "L'article a ete trouve dans la BDD"),
      @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec le CODE fourni")
  })
  ProduitDto findByCodeProduit(@PathVariable("codeProduit") String codeProduit);

  @GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Renvoi la liste des articles", notes = "Cette methode permet de chercher et renvoyer la liste des articles qui existent "
      + "dans la BDD", responseContainer = "List<ProduitDto>")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "La liste des article / Une liste vide")
  })
  List<ProduitDto> findAll();

  //@GetMapping(value = APP_ROOT + "/articles/historique/vente/{idProduit}", produces = MediaType.APPLICATION_JSON_VALUE)
 // List<LigneVenteDto> findHistoriqueVentes(@PathVariable("idProduit") Integer idProduit);

  @GetMapping(value = APP_ROOT + "/articles/historique/commandeclient/{idProduit}", produces = MediaType.APPLICATION_JSON_VALUE)
  List<LigneCommandeEntrepotDto> findHistoriaueCommandeClient(@PathVariable("idProduit") Integer idProduit);

  @GetMapping(value = APP_ROOT + "/articles/historique/commandefournisseur/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
  List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(@PathVariable("idProduit") Integer idProduit);

  @GetMapping(value = APP_ROOT + "/articles/filter/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
  List<ProduitDto> findAllProduitByIdCategory(@PathVariable("idCategory") Integer idCategory);

  @DeleteMapping(value = APP_ROOT + "/articles/delete/{idProduit}")
  @ApiOperation(value = "Supprimer un article", notes = "Cette methode permet de supprimer un article par ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "L'article a ete supprime")
  })
  void delete(@PathVariable("idProduit") Integer id);

}
