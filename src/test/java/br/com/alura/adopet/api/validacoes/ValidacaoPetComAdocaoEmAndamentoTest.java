package br.com.alura.adopet.api.validacoes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;



@ExtendWith(MockitoExtension.class)
public class ValidacaoPetComAdocaoEmAndamentoTest {


    @InjectMocks
    private ValidacaoPetComAdocaoEmAndamento validacao;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    void deveLancarExcecaoQuandoPetJaTemAdocaoEmAndamento() {
        // Arrange
        BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO))
                .willReturn(true);

        // Act & Assert
        Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(dto));
    }
    

    @Test
    void naoDeveLancarExcecaoQuandoPetNaoTemAdocaoEmAndamento() {
        // Arrange
        BDDMockito.given(adocaoRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO))
                .willReturn(false);

        // Act & Assert
        Assertions.assertDoesNotThrow(() -> validacao.validar(dto));
    }

}
