package teste2.JAVA;

public class Usuario {
    private String nome;
    private String email;
    private String nascimento;
    private String cargo;
    private String empresa;

    public Usuario(String nome, String email, String nascimento, String cargo, String empresa) {
        this.nome = nome;
        this.email = email;
        this.nascimento = nascimento;
        this.cargo = cargo;
        this.empresa = empresa;
    }

    // Getters...
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getNascimento() { return nascimento; }
    public String getCargo() { return cargo; }
    public String getEmpresa() { return empresa; }
}
