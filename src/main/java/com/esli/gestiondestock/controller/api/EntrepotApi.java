package com.esli.gestiondestock.controller.api;

import static com.esli.gestiondestock.utils.Constants.APP_ROOT;

import com.esli.gestiondestock.dto.EntrepotDto;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api("entrepots")
public interface EntrepotApi {

  @PostMapping(value = APP_ROOT + "/entrepots/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  EntrepotDto save(@RequestBody EntrepotDto dto);

  @GetMapping(value = APP_ROOT + "/entrepots/{idEntrepot}", produces = MediaType.APPLICATION_JSON_VALUE)
  EntrepotDto findById(@PathVariable("idEntrepot") Integer id);

  @GetMapping(value = APP_ROOT + "/entrepots/all", produces = MediaType.APPLICATION_JSON_VALUE)
  List<EntrepotDto> findAll();

  @DeleteMapping(value = APP_ROOT + "/entrepots/delete/{idEntrepot}")
  void delete(@PathVariable("idEntrepot") Integer id);

}
