/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario;
import Model.Agenda;
import Model.Task_DoneDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alunos
 */
@WebServlet(name = "Controller_Agenda", urlPatterns = {"/Controller_Agenda"})
public class Controller_Agenda extends HttpServlet {

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
            String message = "Operação não realizada";
            Usuario usu = new Usuario();
            
            if (op.equals("CRIAR")) {
                Agenda a = new Agenda();
                a.setTR_TITULO(request.getParameter("txttitulo"));
                a.setTR_TAREFA(request.getParameter("txttarefa"));               
                Task_DoneDAO tddao = new Task_DoneDAO();               
                try {
                    tddao.cadastrarAgenda(a);
                    List<Agenda> listtarefa = tddao.select();
                    request.setAttribute("listtarefa",listtarefa);
                    request.getRequestDispatcher("agenda.jsp").forward(request, response);
                } catch (ClassNotFoundException ex) {
                    System.out.println("Erro ClassNotFound: " + ex.getMessage());
                } catch (SQLException ex) {
                    System.out.println("Erro SQL: " + ex.getMessage());
                }
            }          
            else if (op.equals("DELETAR")) {
                Agenda a = new Agenda();
                a.setTR_ID(Integer.parseInt(request.getParameter("txtID")));
                Task_DoneDAO tddao = new Task_DoneDAO();
                try {
                    tddao.deletarAgenda(a);
                    List<Agenda> listtarefa = tddao.select();
                    request.setAttribute("listtarefa",listtarefa);
                    request.getRequestDispatcher("agenda.jsp").forward(request, response);
                } catch (ClassNotFoundException ex) {
                    System.out.println("Erro ClassNotFound: " + ex.getMessage());
                } catch (SQLException ex) {
                    System.out.println("Erro SQL: " + ex.getMessage());
                }
            }
                
            else if (op.equals("EDITAR")) {
                Agenda a = new Agenda();
                a.setTR_ID(Integer.parseInt(request.getParameter("txtID")));
                a.setTR_TITULO(request.getParameter("txttitulo"));
                a.setTR_TAREFA(request.getParameter("txttarefa"));
                Task_DoneDAO tddao = new Task_DoneDAO();
                try {
                    tddao.updateAgenda(a);
                    List<Agenda> listtarefa = tddao.select();
                    request.setAttribute("listtarefa",listtarefa);
                    request.getRequestDispatcher("agenda.jsp").forward(request, response);
                } catch (ClassNotFoundException ex) {
                    System.out.println("Erro ClassNotFound: " + ex.getMessage());
                } catch (SQLException ex) {
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
