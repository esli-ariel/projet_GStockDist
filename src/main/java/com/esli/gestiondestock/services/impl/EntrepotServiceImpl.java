package com.esli.gestiondestock.services.impl;

import com.esli.gestiondestock.dto.EntrepotDto;
import com.esli.gestiondestock.dto.EntrepotDto;
import com.esli.gestiondestock.exception.EntityNotFoundException;
import com.esli.gestiondestock.exception.ErrorCodes;
import com.esli.gestiondestock.exception.InvalidEntityException;
import com.esli.gestiondestock.exception.InvalidOperationException;
import com.esli.gestiondestock.model.CommandeFournisseur;
import com.esli.gestiondestock.model.CommandeEntrepot;
import com.esli.gestiondestock.repository.EntrepotRepository;
import com.esli.gestiondestock.repository.CommandeFournisseurRepository;
import com.esli.gestiondestock.repository.CommandeFournisseurRepository;
import com.esli.gestiondestock.repository.EntrepotRepository;
import com.esli.gestiondestock.services.EntrepotService;
import com.esli.gestiondestock.services.EntrepotService;
import com.esli.gestiondestock.validator.EntrepotValidator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EntrepotServiceImpl implements EntrepotService {

  private EntrepotRepository entrepotRepository;
  private CommandeFournisseurRepository commandeFournisseurRepository;

  @Autowired
  public EntrepotServiceImpl(EntrepotRepository entrepotRepository, CommandeFournisseurRepository commandeFournisseurRepository) {
    this.entrepotRepository = entrepotRepository;
    this.commandeFournisseurRepository = commandeFournisseurRepository;
  }

  @Override
  public EntrepotDto save(EntrepotDto dto) {
    List<String> errors = EntrepotValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Entrepot is not valid {}", dto);
      throw new InvalidEntityException("L' Entrepot n'est pas valide", ErrorCodes.ENTREPOT_NOT_VALID, errors);
    }

    return EntrepotDto.fromEntity(
            entrepotRepository.save(
            EntrepotDto.toEntity(dto)
        )
    );
  }

  @Override
  public EntrepotDto findById(Integer id) {
    if (id == null) {
      log.error("Entrepot ID is null");
      return null;
    }
    return entrepotRepository.findById(id)
        .map(EntrepotDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucun Entrepot avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.ENTREPOT_NOT_FOUND)
        );
  }

  @Override
  public List<EntrepotDto> findAll() {
    return entrepotRepository.findAll().stream()
        .map(EntrepotDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Entrepot ID is null");
      return;
    }
    List<CommandeFournisseur> commandeFournisseurs = commandeFournisseurRepository.findAllByEntrepotId(id);
    if (!commandeFournisseurs.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un entrepot qui a deja des commande entrepots",
          ErrorCodes.ENTREPOT_ALREADY_IN_USE);
    }
    entrepotRepository.deleteById(id);
  }
}
