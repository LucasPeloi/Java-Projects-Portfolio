package peloi.lucas.model;

import peloi.lucas.util.ValidadorCpf;

public class Main {
    public static void main(String[] args) {
        System.out.println(ValidadorCpf.isValido("529.982.247-25")); // true
        System.out.println(ValidadorCpf.isValido("111.111.111-11")); // false
        System.out.println(ValidadorCpf.isValido("123.456.789-00")); // false
    }
}
