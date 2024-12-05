package controller;

import database.BairrosDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bairros;
import model.Usuario;

@WebServlet(name = "BairrosController", urlPatterns = {"/Bairros-controller"})
public class BairrosController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String pagina = request.getParameter("pagina");
        Usuario user = (Usuario)request.getSession().getAttribute("userLogged");
                
        if (pagina.equals("cadastro")) {
            String titulo = request.getParameter("titulo");
            String descricao = request.getParameter("descricao");
            String statusBairros = request.getParameter("status");
            
            Bairros t = new Bairros(titulo, descricao, statusBairros, user);

            try {
                BairrosDAO tDao = new BairrosDAO();
                tDao.setNewTask(t, user.getId());

                response.sendRedirect("Bairross.jsp");

            } catch (SQLException | ClassNotFoundException erro) {
                System.err.println(erro);
            }
        }
        
        
        if(pagina.equals("excluir")) {
            int id = Integer.parseInt( request.getParameter("id") );
            
            try {
                BairrosDAO dao = new BairrosDAO();
                dao.deleteTask(id);
                
                response.sendRedirect("Bairross.jsp");
            } catch(ClassNotFoundException | SQLException erro) {
                System.err.println( erro );
            }
        }
        
        
        if(pagina.equals("editar")) {
            int id = Integer.parseInt( request.getParameter("id") );
            
            try {
                BairrosDAO dao = new BairrosDAO();
                Bairros t = dao.getOneTask(id);
                
                request.setAttribute("task", t);
                request.getRequestDispatcher("edita-Bairros.jsp").forward(request, response);
            } catch(ClassNotFoundException | SQLException erro) {
                System.err.println( erro );
            }
        }        
        
    
        if (pagina.equals("atualizar")) {
            int id = Integer.parseInt( request.getParameter("id") );
            String titulo = request.getParameter("titulo");
            String descricao = request.getParameter("descricao");
            String statusBairros = request.getParameter("status");

            Bairros t = new Bairros(id, titulo, descricao, statusBairros);

            try {
                BairrosDAO tDao = new BairrosDAO();
                tDao.updateTask(t);

                response.sendRedirect("Bairross.jsp");

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