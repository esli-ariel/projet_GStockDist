package com.esli.gestiondestock.services.strategy;

import com.esli.gestiondestock.dto.ProduitDto;
import com.esli.gestiondestock.exception.ErrorCodes;
import com.esli.gestiondestock.exception.InvalidOperationException;
import com.esli.gestiondestock.services.ProduitService;
import com.esli.gestiondestock.services.FlickrService;
import com.flickr4java.flickr.FlickrException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("produitStrategy")
@Slf4j
public class SaveProduitPhoto implements Strategy<ProduitDto> {

  private FlickrService flickrService;
  private ProduitService produitService;

  @Autowired
  public SaveProduitPhoto(FlickrService flickrService, ProduitService produitService) {
    this.flickrService = flickrService;
    this.produitService = produitService;
  }

  @Override
  public ProduitDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
    ProduitDto produit = produitService.findById(id);
    String urlPhoto = flickrService.savePhoto(photo, titre);
    if (!StringUtils.hasLength(urlPhoto)) {
      throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'produit", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
    }
    produit.setPhoto(urlPhoto);
    return produitService.save(produit);
  }
}
