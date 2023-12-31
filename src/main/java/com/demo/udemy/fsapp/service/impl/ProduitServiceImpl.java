package com.demo.udemy.fsapp.service.impl;

import com.demo.udemy.fsapp.domain.Categorie;
import com.demo.udemy.fsapp.domain.Produit;
import com.demo.udemy.fsapp.dto.ProduitDTO;
import com.demo.udemy.fsapp.repository.ProduitRepository;
import com.demo.udemy.fsapp.service.ProduitService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ProduitDTO saveProduit(ProduitDTO p) {
        return convertEntityToDto(produitRepository.save(convertDTOtoEntity(p)));
        // la methode save de repository retourne une entite
        // Donc on convertit en DTO
    }

    @Override
    public ProduitDTO updateProduit(ProduitDTO p) {
        return convertEntityToDto(produitRepository.save(convertDTOtoEntity(p)));
    }

    @Override
    public void deleteProduit(Produit p) {
        produitRepository.delete(p);
    }

    @Override
    public void deleteProduitById(Long id) {
        produitRepository.deleteById(id);
    }

    @Override
    public ProduitDTO getProduit(Long id) {
        return convertEntityToDto(produitRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Produit non trouvé")
                ));
    }

    @Override // Programmation Fonctionnelle en java
    public List<ProduitDTO> getAllProduits() {
        return  produitRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
        // J'ai une liste d'entité- et avec Java Functional Programming
        // Je vais convertir en stream c'est un mode abstrait qui permet de faire des changements
        // des manipulations sur les éléments de ma liste
        // et grâce à map je peux appliquer ma fonction convertToEntity à tous les élements de ma liste ou ma stream
        // retournée par findAll
        // this:: veut dire que je reference ma méthode que je viens de créer

        // Après avoir traité les streams je reviens à ma liste
    }

    @Override
    public List<Produit> findByNomProduit(String nom) {
        return produitRepository.findByNomProduit(nom);
    }

    @Override
    public List<Produit> findByNomProduitContains(String nom) {
        return produitRepository.findByNomProduitContains(nom);
    }

    @Override
    public List<Produit> findByNomPrix(String nom, Double prix) {
        return produitRepository.findByNomPrix(nom, prix);
    }

    @Override
    public List<Produit> findByCategorie(Categorie categorie) {
        return produitRepository.findByCategorie(categorie);
    }

    @Override
    public List<Produit> findByCategorieIdCat(Long id) {
        return produitRepository.findByCategorieIdCat(id);
    }

    @Override
    public List<Produit> findByOrderByNomProduitAsc() {
        return produitRepository.findByOrderByNomProduitAsc();
    }

    @Override
    public List<Produit> trierProduitsNomsPrix() {
        return produitRepository.trierProduitsNomsPrix();
    }

    @Override
    public ProduitDTO convertEntityToDto(Produit p) {

        //Preciser la strategie de matching de correspondance
        // afin que l'on puisse mapper les elements de categorie quand on les rajoute
        // LOOSE veut dire qu'il ne va pas faire la precision stricte des attributs
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ProduitDTO produitDTO = modelMapper.map(p, ProduitDTO.class);
        return produitDTO;
        // On instancie un nouveau produit DTO et on le remplit avec les attributs de p
       /* ProduitDTO produitDTO = new ProduitDTO();
        produitDTO.setIdProduit(p.getIdProduit());
        produitDTO.setNomProduit(p.getNomProduit());
        produitDTO.setPrixProduit(p.getPrixProduit());
        produitDTO.setCategorie(p.getCategorie());
        return produitDTO; */

        // Design pattern Builder qui nous offre des méthodes et pour chaque attribut j'aurais des méthodes
        // Je recupere les valeurs des attributs à partir du produit p
        // après avoir donné les valeurs je lance la méthode build

        /*
        return ProduitDTO.builder()
                .idProduit(p.getIdProduit())
                .nomProduit(p.getNomProduit())
                .prixProduit(p.getPrixProduit())
                .categorie(p.getCategorie())
               // .nomCategorie(p.getCategorie() != null ? p.getCategorie().getNomCat() : null)
                .dateCreation((p.getDateCreation()))
                .build();
                */

    }

    @Override
    public Produit convertDTOtoEntity(ProduitDTO produitDTO) {

        // Je remplis mon objet produit avec les produitDTO avec les ModelMapper
        Produit produit = new Produit();
        produit = modelMapper.map(produitDTO, Produit.class);

        return produit;
        /*
        Produit produit = new Produit();
        // je remplit mon produit
        produit.setIdProduit(produitDTO.getIdProduit());
        produit.setNomProduit(produitDTO.getNomProduit());
        produit.setPrixProduit(produitDTO.getPrixProduit());
        produit.setDateCreation(produitDTO.getDateCreation());
        produit.setCategorie(produitDTO.getCategorie());

        return produit;
         */
    }

}
