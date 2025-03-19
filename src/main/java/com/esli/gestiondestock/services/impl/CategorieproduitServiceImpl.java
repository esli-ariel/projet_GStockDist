package com.esli.gestiondestock.services.impl;

import com.esli.gestiondestock.dto.CategorieproduitDto;
import com.esli.gestiondestock.exception.EntityNotFoundException;
import com.esli.gestiondestock.exception.ErrorCodes;
import com.esli.gestiondestock.exception.InvalidEntityException;
import com.esli.gestiondestock.exception.InvalidOperationException;
import com.esli.gestiondestock.model.Produit;
import com.esli.gestiondestock.repository.ProduitRepository;
import com.esli.gestiondestock.repository.CategorieproduitRepository;
import com.esli.gestiondestock.services.CategorieproduitService;
import com.esli.gestiondestock.validator.CategorieproduitValidator;
import java.util.List;
import java.util.stream.Collectors;

import com.esli.gestiondestock.services.CategorieproduitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class CategorieproduitServiceImpl implements CategorieproduitService {

  private CategorieproduitRepository categorieproduitRepository;
  private ProduitRepository produitRepository;

  @Autowired
  public CategorieproduitServiceImpl(CategorieproduitRepository categorieproduitRepository, ProduitRepository produitRepository) {
    this.categorieproduitRepository = categorieproduitRepository;
    this.produitRepository = produitRepository;
  }

  @Override
  public CategorieproduitDto save(CategorieproduitDto dto) {
    List<String> errors = CategorieproduitValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Produit is not valid {}", dto);
      throw new InvalidEntityException("La Categorieproduit n'est pas valide", ErrorCodes.CATEGORIEPRODUIT_NOT_VALID, errors);
    }
    return CategorieproduitDto.fromEntity(
        categorieproduitRepository.save(CategorieproduitDto.toEntity(dto))
    );
  }

  @Override
  public CategorieproduitDto findById(Integer id) {
    if (id == null) {
      log.error("Categorieproduit ID is null");
      return null;
    }
    return categorieproduitRepository.findById(id)
        .map(CategorieproduitDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucune categorieproduit avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.CATEGORIEPRODUIT_NOT_FOUND)
        );
  }

  @Override
  public CategorieproduitDto findByCode(String code) {
    if (!StringUtils.hasLength(code)) {
      log.error("categorieproduit CODE is null");
      return null;
    }
    return categorieproduitRepository.findCategorieproduitByCode(code)
        .map(CategorieproduitDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucune categorieproduit avec le CODE = " + code + " n' ete trouve dans la BDD",
            ErrorCodes.CATEGORIEPRODUIT_NOT_FOUND)
        );
  }

  @Override
  public List<CategorieproduitDto> findAll() {
    return categorieproduitRepository.findAll().stream()
        .map(CategorieproduitDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Categorieproduit ID is null");
      return;
    }
    List<Produit> produits = produitRepository.findAllByCategorieproduitId(id);
    if (!produits.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer cette categorie qui est deja utilise",
          ErrorCodes.CATEGORIEPRODUIT_ALREADY_IN_USE);
    }
    categorieproduitRepository.deleteById(id);
  }
}
