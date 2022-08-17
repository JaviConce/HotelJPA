package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import java.util.ArrayList;
import entities.Reserva;

public final class reservas_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=iso-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

    String nhabitacion = (String) request.getParameter("nhabitacion");
    List lr = (List) request.getAttribute("lr");
    if (lr.size()==0){
      out.write("\r\n");
      out.write("        <h5 class=\"bg-primary text-white text-center\">Habitación ");
      out.print(nhabitacion);
      out.write(" sin reservas...</h5>\r\n");
      out.write("    ");
}
    else {
      out.write("\r\n");
      out.write("        <h5 class=\"bg-primary text-white text-center\">Habitación: ");
      out.print(nhabitacion);
      out.write("</h5>\r\n");
      out.write("        <table class=\"mx-auto table table-striped table-responsive\" style=\"width: 80%;\">\r\n");
      out.write("        <thead>\r\n");
      out.write("            <tr class=\"textazulo bgazulc\">\r\n");
      out.write("            <th>N&ordm; Reserva</th>\r\n");
      out.write("            <th>N&ordm; Habitacion</th>\r\n");
      out.write("            <th>Fecha In</th>\r\n");
      out.write("            <th>Fecha Out</th>\r\n");
      out.write("            <th>DNI</th>\r\n");
      out.write("        </tr>\r\n");
      out.write("        </thead>\r\n");
      out.write("        <tbody>\r\n");
      out.write("        ");

        Reserva reserva=null;
        for (int i=0;i<lr.size();i++){
            reserva=(Reserva) lr.get(i); 
      out.write("\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>");
      out.print(reserva.getNreserva());
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print(reserva.getNhabitacion().getNhabitacion() );
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print(reserva.getFechae());
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print(reserva.getFechas());
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print(reserva.getDni());
      out.write("</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("        ");
}
      out.write("\r\n");
      out.write("        </tbody>\r\n");
      out.write("        </table>\r\n");
      out.write("    ");
}

    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
