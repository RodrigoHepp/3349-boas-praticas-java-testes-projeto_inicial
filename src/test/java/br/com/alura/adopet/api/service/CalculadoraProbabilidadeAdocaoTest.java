package br.com.alura.adopet.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;

class CalculadoraProbabilidadeAdocaoTest {

    @Test
    @DisplayName("Probabilidade alta para gatos jovens com peso baixo")
    void probabilidadeAltaPesoBaixoIdadeBaixa() {
        // idade 4 anos e 4 kg - alta
    	 Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                 "Abrigo feliz",
                 "99999999999",
                 "teste@teste.com.br"
         ));
         Pet pet = new Pet(new CadastroPetDto(
                 TipoPet.GATO,
                 "Miau",
                 "Siames",
                 4,
                 "azul",
                 4.0f
         ), abrigo);
         
         CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
         ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

         Assertions.assertEquals(ProbabilidadeAdocao.ALTA,probabilidade);
    }
    
    @Test
    @DisplayName("Probabilidade média para gatos idosos com peso baixo")
    void probabilidadeMediaPesoBaixoIdadeAvancada() {
    	// idade 15 anos e 4 kg - alta
    	 Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                 "Abrigo feliz",
                 "99999999999",
                 "teste@teste.com.br"
         ));
         Pet pet = new Pet(new CadastroPetDto(
                 TipoPet.GATO,
                 "Miau",
                 "Siames",
                 15,
                 "azul",
                 4.0f
         ), abrigo);
         
         CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
         ProbabilidadeAdocao probabilidade = calculadora.calcular(pet);

         Assertions.assertEquals(ProbabilidadeAdocao.MEDIA,probabilidade);
    }
}
