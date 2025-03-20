package com.esli.gestiondestock.controller;

import com.esli.gestiondestock.controller.api.MvtStocksApi;

import java.math.BigDecimal;
import java.util.List;

import com.esli.gestiondestock.dto.MvtStocksDto;
import com.esli.gestiondestock.services.MvtStocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MvtStocksController implements MvtStocksApi {

  private MvtStocksService service;

  @Autowired
  public MvtStocksController(MvtStocksService service) {
    this.service = service;
  }

  @Override
  public BigDecimal stockReelProduit(Integer idProduit) {
    return service.stockReelProduit(idProduit);
  }

  @Override
  public List<MvtStocksDto> mvtStocksProduit(Integer idProduit) {
    return service.mvtStocksProduit(idProduit);
  }

  @Override
  public MvtStocksDto entreeStock(MvtStocksDto dto) {
    return service.entreeStock(dto);
  }

  @Override
  public MvtStocksDto sortieStock(MvtStocksDto dto) {
    return service.sortieStock(dto);
  }

  @Override
  public MvtStocksDto correctionStockPos(MvtStocksDto dto) {
    return service.correctionStockPos(dto);
  }

  @Override
  public MvtStocksDto correctionStockNeg(MvtStocksDto dto) {
    return service.correctionStockNeg(dto);
  }
}
