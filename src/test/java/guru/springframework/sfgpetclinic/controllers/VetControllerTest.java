package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.HashMapModel;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class VetControllerTest {

    VetController vetController;

    HashMapModel model;
    VetService vetService;

    @BeforeEach
    void setUp() {
        model = new HashMapModel();
        vetService = new VetMapService(new SpecialityMapService());
        vetController = new VetController(vetService);
    }

    @Test
    void listVets() {

        Vet vet1 = new Vet(1l, "John", "Doa", new HashSet<Speciality>());
        Vet vet2 = new Vet(2l, "Paul", "Doe", new HashSet<Speciality>());
        Vet vet3 = new Vet(3l, "George", "Doi", new HashSet<Speciality>());
        Vet vet4 = new Vet(4l, "Ringo", "Doo", new HashSet<Speciality>());

        List<Vet> allVets = List.of(vet1, vet2, vet3, vet4);

        allVets.forEach(vetService::save);

        assertAll("List vets assertions",
                () -> assertEquals("vets/index", vetController.listVets(model), "List vets did not return correct route"),
                () -> assertTrue(model.contains("vets"), "List vets did not set vets property"),
                () -> assertEquals(allVets.size(), ((Set) model.getAttribute("vets")).size(), "List vets did not list all existing vets"),
                () -> assertTrue(isEquivalentVetCollection(allVets, ((Set) model.getAttribute("vets"))), "List vets did not list correct vets"));
    }

    boolean isEquivalentVetCollection(Collection<Vet> colA, Collection<Vet> colB) {
        boolean hasSameSize = colA.size() == colB.size();
        boolean hasSameEntries = colA.stream().allMatch(vet -> isVetPresentInCollection(vet, colB));

        return hasSameSize && hasSameEntries;
    }

    boolean isVetPresentInCollection(Vet vet, Collection<Vet> allVets) {
        return allVets.stream().anyMatch(vetInList -> vetIsEqual(vet, vetInList));
    }

    boolean vetIsEqual(Vet vetA, Vet vetB) {
        return vetA.getId().equals(vetB.getId());
    }
}