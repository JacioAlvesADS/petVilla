package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ServicoTest {

    @Test
    void testConstrutor() {
        Servico servico = new Servico("Tosa", "Tosa higiênica", 70.0, 45);
        assertEquals("Tosa", servico.getNome());
        assertEquals("Tosa higiênica", servico.getDescricao());
        assertEquals(70.0, servico.getPreco());
        assertEquals(45, servico.getDuracaoMinutos());
    }

    @Test
    void testSetters() {
        Servico servico = new Servico("Tosa", "Tosa higiênica", 70.0, 45);
        
        servico.setPreco(80.0);
        assertEquals(80.0, servico.getPreco());
        
        servico.setDuracaoMinutos(60);
        assertEquals(60, servico.getDuracaoMinutos());
    }

    @Test
    void testToString() {
        Servico servico = new Servico("Tosa", "Tosa higiênica", 70.0, 45);
        assertEquals("Serviço: Tosa | Preço: R$70.0 | Duração: 45 min", servico.toString());
    }
}