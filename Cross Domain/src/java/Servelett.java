/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gaura
 */
public class Servelett extends HttpServlet {

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
            String[] arrRes = new CDRS().mainCDRS(request.getParameter("ip")).split(",");
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
"      xmlns:h=\"http://xmlns.jcp.org/jsf/html\"\n" +
"      xmlns:f=\"http://xmlns.jcp.org/jsf/core\">\n" +
"    <head>\n" +
"		<title>Home - Home Page</title>\n" +
"		<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
"		<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\" />\n" +
"		<script src=\"js/jquery-1.4.2.min.js\" type=\"text/javascript\"></script>\n" +
"		<script src=\"js/cufon-yui.js\" type=\"text/javascript\"></script>\n" +
"		<script src=\"js/cufon-replace.js\" type=\"text/javascript\"></script>\n" +
"		<script src=\"js/Myriad_Pro_400.font.js\" type=\"text/javascript\"></script>\n" +
"		<script src=\"js/Myriad_Pro_600.font.js\" type=\"text/javascript\"></script>\n" +
"		<script src=\"js/NewsGoth_BT_400.font.js\" type=\"text/javascript\"></script>\n" +
"		<script src=\"js/NewsGoth_BT_700.font.js\" type=\"text/javascript\"></script>\n" +
"		<script src=\"js/NewsGoth_Dm_BT_400.font.js\" type=\"text/javascript\"></script>\n" +
"		<script src=\"js/script.js\" type=\"text/javascript\"></script>\n" +
"	</head>\n" +
"	<body id=\"page1\" style=\"background-color: antiquewhite\">\n" +
"            \n" +
"		<div id=\"main\">\n" +
"                    \n" +
"			<div id=\"header\">		\n" +
"				<div class=\"row-2\">\n" +
"					<div class=\"left\">\n" +
"						<ul>\n" +
"							<li><a href=\"CDRS.xhtml\" class=\"active\"><span>Cross Domain</span></a></li>\n" +
"							<li><a href=\"#\"><span>Logout</span></a></li>\n" +
"						</ul>\n" +
"					</div>\n" +
"				</div>\n" +
"				<div class=\"row-3\">\n" +
"					<center><h2>Cross Domain Recommender System</h2></center>\n" +
"                                       \n" +
"				</div>\n" +
"			</div>\n" +
"		<div id=\"content\">\n" +
"			<div class=\"wrapper\">\n" +
"				<div class=\"col-1\"; style=\"padding-left: 410px\" >\n" +
"                                    <h3 style=\"padding-left: 30px\" >Following are the results based on your search</h3>\n" +
"				<div class=\"col-1\"; style=\"padding-left: 180px\" >\n" +
"						<p><b style=\"padding-left: 90px\" ><u>Queried Book:</u></b> <br><br> " +  arrRes[0]+"<br>"+  arrRes[1]+"<br><br>"
        + "<br><b style=\"padding-left: 60px\"><u>Recommended Movies:</u></b><br></br>"
                                                  +  arrRes[2]+"<br>"+  arrRes[3]+"<br>"+ arrRes[4]+"<br>"+
 "				</div>\n" +
"				</div>\n" +
"			</div>\n" +
"		</div>\n" +
"                    \n" +
"		<div id=\"footer\">\n" +
"			<div class=\"footer-nav\">\n" +
"				<div class=\"left\">\n" +
"					<ul>\n" +
"						<li><a href=\"CDRS.xhtml\">CDRS</a></li>\n" +
"						<li><a href=\"#\">Logout</a></li>\n" +
"					</ul>\n" +
"				</div>\n" +
"			</div>\n" +
"			<div class=\"bottom\">\n" +
"				Copyright - Cross Domain Recommender System with Triple DES Authentication<br />\n" +
"			</div>\n" +
"		</div>\n" +
"	</div>\n" +
"            \n" +
"	<script type=\"text/javascript\"> Cufon.now(); </script>\n" +
"	</body>\n" +
"</html>");
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
