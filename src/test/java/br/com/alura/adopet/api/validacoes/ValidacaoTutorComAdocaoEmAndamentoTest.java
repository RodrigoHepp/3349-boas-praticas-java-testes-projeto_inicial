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
public class ValidacaoTutorComAdocaoEmAndamentoTest {

    @InjectMocks
    private ValidacaoTutorComAdocaoEmAndamento validacao;

    @Mock
    private AdocaoRepository adocaoRepository;

    @Mock
    private SolicitacaoAdocaoDto dto;

    @Test
    void naoDeveriaPermitirSolicitarAdocaoTutorComAdocaoEmAndamento() {     
       BDDMockito.given(adocaoRepository.existsByTutorIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO))
                .willReturn(true);
        Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(dto));
    }


    @Test
    void deveriaPermitirSolicitarAdocaoTutorComAdocaoEmAndamento() {
        BDDMockito.given(adocaoRepository.existsByTutorIdAndStatus(dto.idTutor(), StatusAdocao.AGUARDANDO_AVALIACAO))
                .willReturn(false);
        Assertions.assertDoesNotThrow(() -> validacao.validar(dto));
    }
}
