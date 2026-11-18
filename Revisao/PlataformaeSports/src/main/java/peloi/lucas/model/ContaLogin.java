package peloi.lucas.model;

public class ContaLogin {
    private String email;
    private String senhaHash;

    public ContaLogin(String email, String senhaHash) {
        this.email = email;
        this.senhaHash = senhaHash;
    }

}
