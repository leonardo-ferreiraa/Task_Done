/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Agenda;
import Model.Usuario;
import Model.Task_DoneDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author proft
 */
@WebServlet(name = "Controller_Autenticacao", urlPatterns = {"/Controller_Autenticacao"})
public class Controller_Autenticacao extends HttpServlet {

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
            String mensagem = "";
            String pagina = "";
            Usuario usu = new Usuario();

            String operacao = request.getParameter("operacao");
            if (operacao.equals("Login")) {
                String USU_USUARIO = request.getParameter("txtUSU_USUARIO");
                String USU_SENHA = request.getParameter("txtUSU_SENHA");

                usu.setUSU_USUARIO(USU_USUARIO);
                usu.setUSU_SENHA(USU_SENHA);

                try {
                    if (usu.autenticar()) {
                    Task_DoneDAO pdao = new Task_DoneDAO();
                    try {
                        mensagem = "Logado com sucesso!";
                        List<Agenda> listtarefa = pdao.select();
                        request.setAttribute("listtarefa",listtarefa);
                        request.getRequestDispatcher("agenda.jsp").forward(request,response);
                    } catch (ClassNotFoundException ex) {
                        System.out.println("Erro ClassNotFound: " + ex.getMessage());
                    } catch (SQLException ex) {
                        System.out.println("Erro SQL: " + ex.getMessage());

                }
                    } else {
                        mensagem = "Login ou senha não combinam";
                        pagina = "erroautenticacao.jsp";
                    }

                } catch (SQLException ex) {
                    mensagem = "Erro SQL: " + ex.getMessage();
                    pagina = "erro.jsp";
                } catch (ClassNotFoundException ex) {
                    mensagem = "Erro CNF: " + ex.getMessage();
                    pagina = "erro.jsp";
                }
            }

            request.setAttribute("usu", usu);

            request.setAttribute("mensagem", mensagem);
            request.getRequestDispatcher("/" + pagina).forward(request, response);
            request.setAttribute("mensagem", mensagem);
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
