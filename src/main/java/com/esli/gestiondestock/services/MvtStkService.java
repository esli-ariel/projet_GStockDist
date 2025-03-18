package com.esli.gestiondestock.services;

import com.esli.gestiondestock.dto.MvtStocksDto;
import java.math.BigDecimal;
import java.util.List;

public interface MvtStkService {

  BigDecimal stockReelProduit(Integer idProduit);

  List<MvtStocksDto> mvtStkProduit(Integer idProduit);

  MvtStocksDto entreeStock(MvtStocksDto dto);

  MvtStocksDto sortieStock(MvtStocksDto dto);

  MvtStocksDto correctionStockPos(MvtStocksDto dto);

  MvtStocksDto correctionStockNeg(MvtStocksDto dto);


}
