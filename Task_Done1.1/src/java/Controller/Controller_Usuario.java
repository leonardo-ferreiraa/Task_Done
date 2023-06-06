/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Task_DoneDAO;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alunos
 */
@WebServlet(name = "Controller_Usuario", urlPatterns = {"/Controller_Usuario"})
public class Controller_Usuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String op = request.getParameter("operacao");
            if (op.equals("CADASTRAR")) {
                Usuario u = new Usuario();
                u.setUSU_NOME(request.getParameter("txtnome"));
                u.setUSU_IDADE(Integer.parseInt(request.getParameter("txtidade")));
                u.setUSU_TELEFONE(request.getParameter("txttelefone"));
                u.setUSU_USUARIO(request.getParameter("txtusuario"));
                u.setUSU_SENHA(request.getParameter("txtsenha"));
                
                Task_DoneDAO udao = new Task_DoneDAO();
                
                try {
                    udao.cadastrarUsuario(u);
                    request.setAttribute("Usuario", u);
                    request.getRequestDispatcher("index.html").forward(request, response);
                }
                catch (ClassNotFoundException ex) {
                    System.out.println("Erro ClassNotFound: " + ex.getMessage());
                }catch (SQLException ex) {
                    System.out.println("Erro SQL: " + ex.getMessage());
                }
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
