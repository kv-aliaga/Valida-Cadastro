// LoginDAO adaptado para seguir o padrão do UsuarioDAO
// Nota: Não é boa prática criar a tabela toda vez que for salvar um usuário.

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {
    private String jdbcUrl = "jdbc:postgresql://localhost:5432/login";
    private String username = "postgres";
    private String password = "102023Va";

    public void criarDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
             Statement stmt = con.createStatement()) {

            String SQL = "CREATE TABLE IF NOT EXISTS usuario (" +
                    "id SERIAL PRIMARY KEY," +
                    "nome VARCHAR(100) NOT NULL," +
                    "email VARCHAR(150) UNIQUE NOT NULL," +
                    "nascimento DATE CHECK (EXTRACT (YEAR FROM AGE(nascimento)) BETWEEN 18 AND 100)," +
                    "cargo VARCHAR(50) NOT NULL," +
                    "empresa VARCHAR(100) NOT NULL," +
                    "cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ");";
            stmt.execute(SQL);
            System.out.println("Tabela verificada/criada com sucesso!");
        }
    }
    public void inserir(Usuario usuario) throws ClassNotFoundException, SQLException {
        criarDatabase();
        Class.forName("org.postgresql.Driver");
        String sqlInsert = "INSERT INTO usuario (nome, email, nascimento, cargo, empresa) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setDate(3, Date.valueOf(usuario.getNascimento())); // nascimento deve estar como LocalDate no model
            stmt.setString(4, usuario.getCargo());
            stmt.setString(5, usuario.getEmpresa());
            stmt.executeUpdate();
        }
    }
    public void deletar(String valorDeletado) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String sqlDelete = "DELETE FROM usuario WHERE valorDeletado = ?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement stmt = conn.prepareStatement(sqlDelete)) {

            stmt.setString(1, valorDeletado);
            stmt.executeUpdate();
        }
    }
    public void update(String atributo, String valor) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String sqlUpdate = "UPDATE usuario SET" + atributo +  " = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement stmt = conn.prepareStatement(valor)) {

            stmt.setString(1, valor);
            stmt.executeUpdate();
        }
    }
    public List<Usuario> listarUsuarios() throws ClassNotFoundException, SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        Class.forName("org.postgresql.Driver");
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             Statement stmt = conn.createStatement();
             ResultSet res = stmt.executeQuery("SELECT * FROM usuario")) {

            while (res.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(res.getInt("id"));
                usuario.setNome(res.getString("nome"));
                usuario.setEmail(res.getString("email"));
                usuario.setNascimento(res.getDate("nascimento").toLocalDate());
                usuario.setCargo(res.getString("cargo"));
                usuario.setEmpresa(res.getString("empresa"));
                usuario.setCadastro(res.getTimestamp("cadastro").toLocalDateTime());
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}