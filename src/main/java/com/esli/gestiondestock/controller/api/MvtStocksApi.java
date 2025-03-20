package com.esli.gestiondestock.controller.api;

import static com.esli.gestiondestock.utils.Constants.APP_ROOT;

import com.esli.gestiondestock.dto.MvtStocksDto;
import io.swagger.annotations.Api;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api("mvtstocks")
public interface MvtStocksApi {

  @GetMapping(APP_ROOT + "/mvtStocks/stockreel/{idProduit}")
  BigDecimal stockReelProduit(@PathVariable("idProduit") Integer idProduit);

  @GetMapping(APP_ROOT + "/mvtStocks/filter/produit/{idProduit}")
  List<MvtStocksDto> MvtStocksProduit(@PathVariable("idProduit") Integer idProduit);

  @PostMapping(APP_ROOT + "/mvtStocks/entree")
  MvtStocksDto entreeStock(@RequestBody MvtStocksDto dto);

  @PostMapping(APP_ROOT + "/mvtStocks/sortie")
  MvtStocksDto sortieStock(@RequestBody MvtStocksDto dto);

  @PostMapping(APP_ROOT + "/mvtStocks/correctionpos")
  MvtStocksDto correctionStockPos(@RequestBody MvtStocksDto dto);

  @PostMapping(APP_ROOT + "/mvtStocks/correctionneg")
  MvtStocksDto correctionStockNeg(@RequestBody MvtStocksDto dto);

}
