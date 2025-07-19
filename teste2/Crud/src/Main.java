import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LoginDAO dao = new LoginDAO();

        try {
            // Criar e salvar novo usuário
            Usuario usuario = new Usuario("Jones", "jones@gmail.com", LocalDate.of(2000, 12, 12), "Estudante", "EoTren");
            dao.salvar(usuario);
            System.out.println("Usuário salvo com sucesso!\n");

            // Listar todos usuários
            List<Usuario> usuarios = dao.listarUsuarios();
            for (Usuario u : usuarios) {
                System.out.println(u);
            }

            // Atualizar cargo de um usuário pelo id
            dao.update("cargo", "Desenvolvedor");
            System.out.println("Cargo atualizado com sucesso!\n");

            // Deletar usuário pelo email
            dao.deletar("pedrao@gmail.com");
            System.out.println("Usuário deletado com sucesso!\n");

            // Listar novamente para ver alterações
            usuarios = dao.listarUsuarios();
            for (Usuario u : usuarios) {
                System.out.println(u);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
