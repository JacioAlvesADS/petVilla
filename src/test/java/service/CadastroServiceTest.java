package service;

import model.Dono;
import model.Pet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CadastroServiceTest {

    @Test
    void testCadastrarDono() {
        Dono dono = new Dono("João", "12345678900", "joao@gmail.com", "Rua A");

        CadastroService.cadastrarDono(dono);
        assertTrue(database.BancoDados.donos.contains(dono));
    }

    @Test
    void testCadastrarPet() {
        Pet pet = new Pet("Rex", "Cachorro", "Labrador", 5, "Vacinado");

        CadastroService.cadastrarPet(pet);
        assertTrue(database.BancoDados.pets.contains(pet));
    }
}