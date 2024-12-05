package controller;

import database.DenunciaDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Denuncia;
import model.Usuario;

@WebServlet(name = "DenunciaController", urlPatterns = {"/denuncia-controller"})
public class DenunciaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String pagina = request.getParameter("pagina");
        Usuario user = (Usuario)request.getSession().getAttribute("userLogged");
                
        if (pagina.equals("cadastro")) {
            String denuncia = request.getParameter("denuncia");
            String descricao = request.getParameter("descricao");
            String statusDenuncia = request.getParameter("status");
            
            Denuncia d = new Denuncia(denuncia, descricao, statusDenuncia, user);

            try {
                DenunciaDAO tDao = new DenunciaDAO();
                tDao.setNewDenuncia(d, user.getId());

                response.sendRedirect("denuncias.jsp");

            } catch (SQLException | ClassNotFoundException erro) {
                System.err.println(erro);
            }
        }
        
        
        if(pagina.equals("excluir")) {
            int id = Integer.parseInt( request.getParameter("id") );
            
            try {
                DenunciaDAO dao = new DenunciaDAO();
                dao.deleteDenuncia(id);
                
                response.sendRedirect("denuncias.jsp");
            } catch(ClassNotFoundException | SQLException erro) {
                System.err.println( erro );
            }
        }
        
        
        if(pagina.equals("editar")) {
            int id = Integer.parseInt( request.getParameter("id") );
            
            try {
                DenunciaDAO dao = new DenunciaDAO();
                Denuncia d = dao.getOneDenuncia(id);
                
                request.setAttribute("denuncia", d);
                request.getRequestDispatcher("edita-denuncia.jsp").forward(request, response);
            } catch(ClassNotFoundException | SQLException erro) {
                System.err.println( erro );
            }
        }        
        
    
        if (pagina.equals("atualizar")) {
            int id = Integer.parseInt( request.getParameter("id") );
            String denuncia = request.getParameter("denuncia");
            String descricao = request.getParameter("descricao");
            String statusDenuncia = request.getParameter("status");

            Denuncia t = new Denuncia(id, denuncia, descricao, statusDenuncia);

            try {
                DenunciaDAO tDao = new DenunciaDAO();
                tDao.updateDenuncia(t);

                response.sendRedirect("denuncias.jsp");

            } catch (SQLException | ClassNotFoundException erro) {
                System.err.println(erro);
            }
        }
    
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}