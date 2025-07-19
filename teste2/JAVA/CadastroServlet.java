package teste2.JAVA;

import teste2.JAVA.UsuarioDAO;
import teste2.JAVA.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String nascimento = request.getParameter("nascimento");
        String cargo = request.getParameter("cargo");
        String empresa = request.getParameter("empresa");

        Usuario usuario = new Usuario(nome, email, nascimento, cargo, empresa);
        UsuarioDAO dao = new UsuarioDAO();
        dao.inserir(usuario);

        response.sendRedirect("sucesso.html");
    }
}
