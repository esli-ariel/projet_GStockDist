package com.esli.gestiondestock.services.impl;

import com.esli.gestiondestock.dto.MagasinDto;
import com.esli.gestiondestock.exception.EntityNotFoundException;
import com.esli.gestiondestock.exception.ErrorCodes;
import com.esli.gestiondestock.exception.InvalidEntityException;
import com.esli.gestiondestock.exception.InvalidOperationException;
import com.esli.gestiondestock.model.CommandeEntrepot;
import com.esli.gestiondestock.repository.CommandeEntrepotRepository;
import com.esli.gestiondestock.repository.MagasinRepository;
import com.esli.gestiondestock.services.MagasinService;
import com.esli.gestiondestock.validator.MagasinValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MagasinServiceImpl implements MagasinService {

  private MagasinRepository magasinRepository;
  private CommandeEntrepotRepository commandeEntrepotRepository;

  @Autowired
  public MagasinServiceImpl(MagasinRepository magasinRepository, CommandeEntrepotRepository commandeEntrepotRepository) {
    this.magasinRepository = magasinRepository;
    this.commandeEntrepotRepository = commandeEntrepotRepository;
  }

  @Override
  public MagasinDto save(MagasinDto dto) {
    List<String> errors = MagasinValidator.validate(dto);
    if (!errors.isEmpty()) {
      log.error("Magasin is not valid {}", dto);
      throw new InvalidEntityException("Le magasin n'est pas valide", ErrorCodes.MAGASIN_NOT_VALID, errors);
    }

    return MagasinDto.fromEntity(
        magasinRepository.save(
            MagasinDto.toEntity(dto)
        )
    );
  }

  @Override
  public MagasinDto findById(Integer id) {
    if (id == null) {
      log.error("Entrepot ID is null");
      return null;
    }
    return magasinRepository.findById(id)
        .map(MagasinDto::fromEntity)
        .orElseThrow(() -> new EntityNotFoundException(
            "Aucun Magasin avec l'ID = " + id + " n' ete trouve dans la BDD",
            ErrorCodes.MAGASIN_NOT_FOUND)
        );
  }

  @Override
  public List<MagasinDto> findAll() {
    return magasinRepository.findAll().stream()
        .map(MagasinDto::fromEntity)
        .collect(Collectors.toList());
  }

  @Override
  public void delete(Integer id) {
    if (id == null) {
      log.error("Magasin ID is null");
      return;
    }
    List<CommandeEntrepot> commandeEntrepots = commandeEntrepotRepository.findAllByEntrepotId(id);
    if (!commandeEntrepots.isEmpty()) {
      throw new InvalidOperationException("Impossible de supprimer un magasin qui a deja des commande magasins",
          ErrorCodes.ENTREPOT_ALREADY_IN_USE);
    }
    magasinRepository.deleteById(id);
  }
}
