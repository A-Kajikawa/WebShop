package shop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.ShohinArrayBean;
import Bean.ShohinBean;

/**
 * Servlet implementation class ShohinDetailServlet
 */
@WebServlet("/ShohinDetailServlet")
public class ShohinDetailServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    ShohinArrayBean shohinArrayBean = new ShohinArrayBean();
    //商品コードの受け取り
    String code = request.getParameter("code");


    //商品コードから商品情報検索
    try{
      Class.forName("com.mysql.jdbc.Driver");
    }catch (Exception e) {
      e.printStackTrace();
    }

    String codesql = "SELECT * FROM SHOHIN WHERE SHOHIN_CODE = ?;";

    try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s2a1sampledb?serverTimezone=JST", "root","password");
        PreparedStatement stmt = conn.prepareStatement(codesql)){
        stmt.setString(1, code);
        try(ResultSet rs = stmt.executeQuery()){
            if(rs.next()){
            //検索結果がある場合は商品情報を取得
            ShohinBean shohinBean = new ShohinBean();
            shohinBean.setShohin_code(rs.getString("SHOHIN_CODE"));
            shohinBean.setShohin_name(rs.getString("SHOHIN_NAME"));
            shohinBean.setShohin_val(rs.getString("SHOHIN_VOL"));
            shohinBean.setShohin_price(rs.getInt("SHOHIN_PRICE"));
            shohinBean.setShohin_area(rs.getString("SHOHIN_AREA"));
            shohinBean.setShohin_comment(rs.getString("SHOHIN_COMMENT"));
            shohinBean.setShohin_image(rs.getString("SHOHIN_IMAGE"));
            shohinArrayBean.addShohinRecordArray(shohinBean);
            HttpSession session = request.getSession();
            session.setAttribute("shohinBean", shohinBean);
            getServletContext().getRequestDispatcher("/shohin_detail.jsp").forward(request, response);
            }else{
            //検索結果がない場合は例外を自分で発生させてエラーにする
              throw new SQLException("商品コードが見つかりません");
            }
          }catch(Exception e){
            e.printStackTrace();
          }
      }catch(Exception e){
        e.printStackTrace();
    }
  }
}







