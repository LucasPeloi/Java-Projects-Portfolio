package peloi.lucas.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorCpfTest {

    @Test
    void deveAceitarCpfValidoComPontuacao() {
        assertTrue(ValidadorCpf.isValido("529.982.247-25"));
    }

    @Test
    void deveAceitarCpfValidoSemPontuacao() {
        assertTrue(ValidadorCpf.isValido("52998224725"));
    }

    @Test
    void deveRejeitarDigitosVerificadoresErrados() {
        assertFalse(ValidadorCpf.isValido("123.456.789-00"));
    }

    @Test
    void deveRejeitarSequenciaRepetida() {
        assertFalse(ValidadorCpf.isValido("111.111.111-11"));
    }

    @Test
    void deveRejeitarTamanhoErrado() {
        assertFalse(ValidadorCpf.isValido("123"));
    }

    @Test
    void deveRejeitarNuloEVazio() {
        assertFalse(ValidadorCpf.isValido(null));
        assertFalse(ValidadorCpf.isValido(""));
    }
}