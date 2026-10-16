package peloi.lucas.util;

public final class ValidadorCpf {
    private ValidadorCpf() {}

    public static String limpar(String cpf){
        if(cpf == null) {
            cpf = "";
        }else {
            cpf = cpf.replaceAll("[^0-9]", "");
        }
        return cpf;
    }

    public static boolean isValido(String cpf){
        String cpfLimpo = limpar(cpf);

        if(cpfLimpo.length() != 11) return false;
        if (cpfLimpo.chars().distinct().count() == 1) return false;

        int d1 = calcularDigito(cpfLimpo,9);
        int d2 = calcularDigito(cpfLimpo,10);

        return d1 == Character.getNumericValue(cpfLimpo.charAt(9)) && d2 == Character.getNumericValue(cpfLimpo.charAt(10));
    }

    public static int calcularDigito(String numeros, int quantidade){
        int soma = 0;
        int peso = quantidade + 1;

        for(int i =0; i<quantidade; i++){
            soma += Character.getNumericValue(numeros.charAt(i)) * peso--;
        }
        int resto = (soma * 10 ) %11;

        if (resto == 10){
            return 0;
        }else {
            return resto;
        }
    }
}
