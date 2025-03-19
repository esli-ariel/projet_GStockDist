package com.esli.gestiondestock.services.impl;

import com.esli.gestiondestock.dto.MvtStocksDto;
import com.esli.gestiondestock.exception.ErrorCodes;
import com.esli.gestiondestock.exception.InvalidEntityException;
import com.esli.gestiondestock.model.TypeMvtStock;
import com.esli.gestiondestock.repository.MvtStocksRepository;
import com.esli.gestiondestock.services.ProduitService;
import com.esli.gestiondestock.services.MvtStocksService;
import com.esli.gestiondestock.validator.MvtStocksValidator;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MvtStocksServiceImpl implements MvtStocksService {

  private MvtStocksRepository repository;
  private ProduitService ProduitService;

  @Autowired
  public MvtStocksServiceImpl(MvtStocksRepository repository, ProduitService ProduitService) {
    this.repository = repository;
    this.ProduitService = ProduitService;
  }

  @Override
  public BigDecimal stockReelProduit(Integer idProduit) {
    if (idProduit == null) {
      log.warn("ID Produit is NULL");
      return BigDecimal.valueOf(-1);
    }
    ProduitService.findById(idProduit);
    return repository.stockReelProduit(idProduit);
  }

  @Override
  public List<MvtStocksDto> mvtStkProduit(Integer idProduit) {
    return List.of();
  }

  @Override
  public List<MvtStocksDto> MvtStocksProduit(Integer idProduit) {
    return repository.findAllByProduitId(idProduit).stream()
        .map(MvtStocksDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public MvtStocksDto entreeStock(MvtStocksDto dto) {
    return entreePositive(dto, TypeMvtStock.ENTREE);
  }

  @Override
  public MvtStocksDto sortieStock(MvtStocksDto dto) {
    return sortieNegative(dto, TypeMvtStock.SORTIE);
  }

  @Override
  public MvtStocksDto correctionStockPos(MvtStocksDto dto) {
    return entreePositive(dto, TypeMvtStock.CORRECTION_POS);
  }

  @Override
  public MvtStocksDto correctionStockNeg(MvtStocksDto dto) {
    return sortieNegative(dto, TypeMvtStock.CORRECTION_NEG);
  }

  private MvtStocksDto entreePositive(MvtStocksDto dto, TypeMvtStock typeMvtStock) {
    List<String> errors = MvtStocksValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Produit is not valid {}", dto);
      throw new InvalidEntityException("Le mouvement du stock n'est pas valide", ErrorCodes.MVT_STOCK_NOT_VALID, errors);
    }
    dto.setQuantite(
        BigDecimal.valueOf(
            Math.abs(dto.getQuantite().doubleValue())
        )
    );
    dto.setTypeMvt(typeMvtStock);
    return MvtStocksDto.fromEntity(
        repository.save(MvtStocksDto.toEntity(dto))
    );
  }

  private MvtStocksDto sortieNegative(MvtStocksDto dto, TypeMvtStock typeMvtStock) {
    List<String> errors = MvtStocksValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Produit is not valid {}", dto);
      throw new InvalidEntityException("Le mouvement du stock n'est pas valide", ErrorCodes.MVT_STOCK_NOT_VALID, errors);
    }
    dto.setQuantite(
        BigDecimal.valueOf(
            Math.abs(dto.getQuantite().doubleValue()) * -1
        )
    );
    dto.setTypeMvt(typeMvtStock);
    return MvtStocksDto.fromEntity(
        repository.save(MvtStocksDto.toEntity(dto))
    );
  }
}
