package teste2.JAVA;

import teste2.JAVA.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UsuarioDAO {
    private String url = "jdbc:postgresql://localhost:5432/seu_banco";
    private String user = "seu_usuario";
    private String password = "sua_senha";

    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO login_site (nome, email, nascimento, cargo, empresa, cadastro) VALUES (?, ?, ?, ?, ?, current_timestamp)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getNascimento());
            stmt.setString(4, usuario.getCargo());
            stmt.setString(5, usuario.getEmpresa());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
