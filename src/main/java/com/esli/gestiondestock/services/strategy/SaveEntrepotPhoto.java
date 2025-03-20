package com.esli.gestiondestock.services.strategy;

import com.esli.gestiondestock.dto.EntrepotDto;
import com.esli.gestiondestock.dto.EntrepotDto;
import com.esli.gestiondestock.exception.ErrorCodes;
import com.esli.gestiondestock.exception.InvalidOperationException;
import com.esli.gestiondestock.services.EntrepotService;
import com.esli.gestiondestock.services.FlickrService;
import com.flickr4java.flickr.FlickrException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("entrepotStrategy")
@Slf4j
public class SaveEntrepotPhoto implements Strategy<EntrepotDto> {

  private FlickrService flickrService;
  private EntrepotService entrepotService;

  @Autowired
  public SaveEntrepotPhoto(FlickrService flickrService, EntrepotService EntrepotService) {
    this.flickrService = flickrService;
    this.entrepotService = entrepotService;
  }

  @Override
  public EntrepotDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
    EntrepotDto entrepot = entrepotService.findById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);
    if (!StringUtils.hasLength(urlPhoto)) {
      throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'entrepot", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
    }
    entrepot.setPhoto(urlPhoto);
    return entrepotService.save(entrepot);
  }
}
