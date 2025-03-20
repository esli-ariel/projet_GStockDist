package com.esli.gestiondestock.controller.api;

import static com.esli.gestiondestock.utils.Constants.APP_ROOT;

import com.esli.gestiondestock.dto.CategorieproduitDto;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api("categories")
public interface CategorieproduitApi {

  @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Enregistrer une categorie", notes = "Cette methode permet d'enregistrer ou modifier une categorie", response =
      CategorieproduitDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "L'objet Categorieproduit cree / modifie"),
      @ApiResponse(code = 400, message = "L'objet Categorieproduit n'est pas valide")
  })
  CategorieproduitDto save(@RequestBody CategorieproduitDto dto);

  @GetMapping(value = APP_ROOT + "/categories/{idCategorieproduit}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Rechercher une categorie par ID", notes = "Cette methode permet de chercher une categorie par son ID", response =
      CategorieproduitDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "La categorie a ete trouve dans la BDD"),
      @ApiResponse(code = 404, message = "Aucune categorie n'existe dans la BDD avec l'ID fourni")
  })
  CategorieproduitDto findById(@PathVariable("idCategorieproduit") Integer idCategorieproduit);

  @GetMapping(value = APP_ROOT + "/categories/filter/{codeCategorieproduit}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Rechercher une categorie par CODE", notes = "Cette methode permet de chercher une categorie par son CODE", response =
      CategorieproduitDto.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "L'article a ete trouve dans la BDD"),
      @ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec le CODE fourni")
  })
  CategorieproduitDto findByCode(@PathVariable("codeCategorieproduit") String codeCategorieproduit);

  @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiOperation(value = "Renvoi la liste des categories", notes = "Cette methode permet de chercher et renvoyer la liste des categories qui existent "
      + "dans la BDD", responseContainer = "List<CategorieproduitDto>")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "La liste des article / Une liste vide")
  })
  List<CategorieproduitDto> findAll();

  @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategorieproduit}")
  @ApiOperation(value = "Supprimer un article", notes = "Cette methode permet de supprimer une categorie par ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "La categorie a ete supprime")
  })
  void delete(@PathVariable("idCategorieproduit") Integer id);

}
