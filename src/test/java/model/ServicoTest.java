package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServicoTest {

    @Test
    void testCriacaoServico() {
        Servico servico = new Servico("Banho", "Banho com shampoo e secagem", 50.0, 30);

        assertEquals("Banho", servico.getNome());
        assertEquals("Banho com shampoo e secagem", servico.getDescricao());
        assertEquals(50.0, servico.getPreco());
        assertEquals(30, servico.getDuracaoMinutos());
    }

    @Test
    void testSettersServico() {
        Servico servico = new Servico("Banho", "Banho simples", 40.0, 20);

        servico.setDescricao("Banho completo");
        servico.setPreco(60.0);
        servico.setDuracaoMinutos(40);

        assertEquals("Banho completo", servico.getDescricao());
        assertEquals(60.0, servico.getPreco());
        assertEquals(40, servico.getDuracaoMinutos());
    }

    @Test
    void testToString() {
        Servico servico = new Servico("Tosa", "Tosa completa com tesoura", 70.0, 45);
        String esperado = "Serviço: Tosa, Preço: R$70.0, Duração: 45 min";
        assertEquals(esperado, servico.toString());
    }
}