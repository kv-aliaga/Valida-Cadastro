import java.time.LocalDate;
import java.time.LocalDateTime;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private LocalDate nascimento;
    private String cargo;
    private String empresa;
    private LocalDateTime cadastro;

    public Usuario() {
    }

    public Usuario(String nome, String email, LocalDate nascimento, String cargo, String empresa) {
        this.nome = nome;
        this.email = email;
        this.nascimento = nascimento;
        this.cargo = cargo;
        this.empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public LocalDateTime getCadastro() {
        return cadastro;
    }

    public void setCadastro(LocalDateTime cadastro) {
        this.cadastro = cadastro;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", nascimento=" + nascimento +
                ", cargo='" + cargo + '\'' +
                ", empresa='" + empresa + '\'' +
                ", cadastro=" + cadastro +
                '}';
    }
}