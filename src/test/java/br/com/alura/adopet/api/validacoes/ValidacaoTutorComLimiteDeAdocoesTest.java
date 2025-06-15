package br.com.alura.adopet.api.validacoes;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;

@ExtendWith(MockitoExtension.class)
public class ValidacaoTutorComLimiteDeAdocoesTest {

    @Mock
    private ValidacaoTutorComLimiteDeAdocoes validacao;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private SolicitacaoAdocaoDto dto;

   
    @Test
    void naoDeveriaPermitirSolicitacaoDeAdocaoTutorAtingiuLimiteDe5Adocoes() {
        //Arrange
        BDDMockito.given(adocaoRepository.countByTutorIdAndStatus(dto.idTutor(),StatusAdocao.APROVADO)).willReturn(5);

        //Act + Assert
        assertThrows(ValidacaoException.class,() ->validacao.validar(dto));
    }

    @Test
    void deveriaPermitirSolicitacaoDeAdocaoTutorNaoAtingiuLimiteDe5Adocoes() {
        //Arrange
        BDDMockito.given(adocaoRepository.countByTutorIdAndStatus(dto.idTutor(),StatusAdocao.APROVADO)).willReturn(4);

        //Act + Assert
        assertDoesNotThrow(() -> validacao.validar(dto));
    }

}
