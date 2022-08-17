/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entities.Habitacion;
import entities.Ocupacion;
import entities.OcupacionPK;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author pacopulido
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    private EntityTransaction transaction;

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
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;
        
        String op;
        List lhl;
        List lh;
        String sql;
        Query query;
        
        EntityManager em = (EntityManager) session.getAttribute("em");
        if (em == null) {
            em = jpautil.JPAUtil.getEntityManagerFactory().createEntityManager();
            session.setAttribute("em", em);
        }

            op = request.getParameter("op");

            if (op.equals("inicio")) {
                sql = "select h from Habitacion h where h.ocupada=false";
                query = em.createQuery(sql);
                lhl = query.getResultList();
                session.setAttribute("lhl", lhl);

                sql = "select h from Habitacion h";
                query = em.createQuery(sql);
                lh = query.getResultList();
                session.setAttribute("lh", lh);
                session.setAttribute("ficha", "entrada");
                dispatcher = request.getRequestDispatcher("home.jsp");
                dispatcher.forward(request, response);

            } else if (op.equals("entradaoreserva")) {
            String ficha = request.getParameter("ficha");
            session.setAttribute("ficha", ficha);
            dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);

        } else if (op.equals("hospedar")) {
            String dni = (String) request.getParameter("dni");
            String nhabitacion = (String) request.getParameter("nhabitacion");
            String fechas = (String) request.getParameter("fecha");
            fechas = fechas.replace("/", "-");
            Date fechae = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String fechaeTXT = sdf.format(fechae);
            // creamos nuestro objeto Ocupacion
            OcupacionPK ocupacionPK = new OcupacionPK(nhabitacion, fechaeTXT);
            Habitacion habitacion = (Habitacion) em.find(Habitacion.class, nhabitacion);
            Ocupacion ocupacion = new Ocupacion(ocupacionPK, fechas, dni);

            transaction = em.getTransaction();
            transaction.begin();
            em.persist(ocupacion);
            habitacion.setOcupada(Short.valueOf("1"));
            em.merge(habitacion);
            transaction.commit();

            lhl = (List) session.getAttribute("lhl");
            for (int i = 0; i < lhl.size(); i++) {
                Habitacion h = (Habitacion) lhl.get(i);
                if (h.getNhabitacion().equals(habitacion.getNhabitacion())) {
                    lhl.remove(i);
                }
            }
            
            session.setAttribute("lhl", lhl);
            String mensaje = "Hospedado " + ocupacion.getDni() + " en Habitacion " + ocupacion.getOcupacionPK().getNhabitacion();
            request.setAttribute("mensaje", mensaje);
            dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);

        } else if (op.equals("reservas")) {  // aquí no se llega->se llamó a la vista con ajax desde mainview
            String nhabitacion = (String) request.getParameter("nhabitacion");
            sql = "select r from Reserva r where r.nhabitacion.nhabitacion=:nhabitacion";
            query = em.createQuery(sql);
            query.setParameter("nhabitacion", nhabitacion);
            List lr = query.getResultList();
            request.setAttribute("lr", lr);
            dispatcher = request.getRequestDispatcher("reservas.jsp");
            dispatcher.forward(request, response);
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
