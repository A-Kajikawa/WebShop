package shop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.CartArrayBean;

/**
 * Servlet implementation class DeleteCartServlet
 */
@WebServlet("/DeleteCartServlet")
public class DeleteCartServlet extends HttpServlet {

	@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession session = request.getSession();
	  CartArrayBean cartArrayBean = (CartArrayBean)session.getAttribute("cartArrayBean");
	  String code = request.getParameter("code");
	  cartArrayBean.delCartArray(code);
	  session.setAttribute("cartArrayBean ", cartArrayBean);
    getServletContext().getRequestDispatcher("/shohin_cart.jsp").forward(request, response);
	}
}
