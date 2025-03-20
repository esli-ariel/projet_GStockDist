package com.esli.gestiondestock.controller.api;

import com.esli.gestiondestock.dto.MagasinDto;
import com.esli.gestiondestock.dto.MagasinDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.esli.gestiondestock.utils.Constants.APP_ROOT;

@Api("magasins")
public interface MagasinApi {

  @PostMapping(value = APP_ROOT + "/magasins/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  MagasinDto save(@RequestBody MagasinDto dto);

  MagasinDto save(MagasinDto dto);

  @GetMapping(value = APP_ROOT + "/magasins/{idMagasin}", produces = MediaType.APPLICATION_JSON_VALUE)
  MagasinDto findById(@PathVariable("idMagasin") Integer id);

  @GetMapping(value = APP_ROOT + "/magasins/all", produces = MediaType.APPLICATION_JSON_VALUE)
  List<MagasinDto> findAll();

  @DeleteMapping(value = APP_ROOT + "/magasins/delete/{idMagasin}")
  void delete(@PathVariable("idMagasin") Integer id);

}
