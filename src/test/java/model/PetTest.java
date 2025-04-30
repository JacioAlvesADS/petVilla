package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PetTest {

    @Test
    void testCriacaoPet() {
        Pet pet = new Pet("Rex", "Cachorro", "Labrador", 5, "Vacinado");

        assertEquals("Rex", pet.getNome());
        assertEquals("Cachorro", pet.getEspecie());
        assertEquals("Labrador", pet.getRaca());
        assertEquals(5, pet.getIdade());
        assertEquals("Vacinado", pet.getHistoricoMedico());
    }

    @Test
    void testSettersPet() {
        Pet pet = new Pet("Rex", "Cachorro", "Labrador", 5, "Vacinado");

        pet.setNome("Max");
        pet.setEspecie("Gato");
        pet.setRaca("Siamês");
        pet.setIdade(3);
        pet.setHistoricoMedico("Castrado");

        assertEquals("Max", pet.getNome());
        assertEquals("Gato", pet.getEspecie());
        assertEquals("Siamês", pet.getRaca());
        assertEquals(3, pet.getIdade());
        assertEquals("Castrado", pet.getHistoricoMedico());
    }

    @Test
    void testToString() {
        Pet pet = new Pet("Rex", "Cachorro", "Labrador", 5, "Vacinado");
        String esperado = "Pet: Rex, Espécie: Cachorro, Raça: Labrador, Idade: 5, Histórico Médico: Vacinado";
        assertEquals(esperado, pet.toString());
    }
}