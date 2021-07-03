package shop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.ShohinArrayBean;
import Bean.ShohinBean;


/**
 * Servlet implementation class ShohinQueryServlet
 */
@WebServlet("/ShohinQueryServlet")
public class ShohinQueryServlet extends HttpServlet {

	@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  //ShohinArrayBeanをインスタンス化
	  ShohinArrayBean shohinArrayBean = new ShohinArrayBean();
	  //受け取る値の変数の初期化
	  String name = null;
	  String price = null;
	  String radio = null;

	  //DB準備をする
	  try{
      Class.forName("com.mysql.jdbc.Driver");
    }catch (Exception e) {
      e.printStackTrace();
    }

	  //キーワードを取得する
	  //ラジオボタンの情報を取得("shohin" or "price")

	  try {
	  radio = request.getParameter("radio");
	  String keyword = request.getParameter("keyword");
	  //ラジオボタンが商品または例外の場合
	  if(keyword.equals("")) {
	    getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
	    }
	  }catch(Exception e) {
	    getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
	  }

	  if(radio.equals("shohin")){
      name = request.getParameter("keyword");
   //ラジオボタンが価格または例外の場合
     }else{
      price = request.getParameter("keyword");
     }

	    //キーワード検索をする
	    String shohinsql = "SELECT * FROM SHOHIN WHERE SHOHIN_NAME like ?;";

	    //ラジオボタンが商品の場合、商品検索をする
	    if(radio.equals("shohin")){
	    try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s2a1sampledb?serverTimezone=JST", "root","password");
	        PreparedStatement stmt = conn.prepareStatement(shohinsql)){
	        stmt.setString(1, "%"+ name +"%");
	        try(ResultSet rs=stmt.executeQuery()){

	          while(rs.next()) {
	          // 各種情報をＢｅａｎに保存
	          ShohinBean shohinBean = new ShohinBean();
	          shohinBean.setShohin_code(rs.getString("SHOHIN_CODE"));
	          shohinBean.setShohin_name(rs.getString("SHOHIN_NAME"));
	          shohinBean.setShohin_val(rs.getString("SHOHIN_VOL"));
	          shohinBean.setShohin_price(rs.getInt("SHOHIN_PRICE"));
	          shohinBean.setShohin_area(rs.getString("SHOHIN_AREA"));
	          shohinBean.setShohin_comment(rs.getString("SHOHIN_COMMENT"));
	          shohinBean.setShohin_image(rs.getString("SHOHIN_IMAGE"));
	          shohinArrayBean.addShohinRecordArray(shohinBean);
	          }
	          if(shohinArrayBean.getArraySize()==0) {
              getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
            }else {
            HttpSession session = request.getSession();
            session.setAttribute("shohinArrayBean", shohinArrayBean);
            session.setAttribute("radio",radio);
            session.setAttribute("keyword", name);
            getServletContext().getRequestDispatcher("/shohin_retrieval.jsp").forward(request, response);
            }
	        }catch(Exception e){
	          e.printStackTrace();
	        }
	      }catch(Exception e){
	        e.printStackTrace();
	      }


	    //ラジオボタンが価格の場合、価格検索をする
	    }else{
	      String pricesql = "SELECT * FROM SHOHIN WHERE SHOHIN_PRICE = ? ;";
	      try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s2a1sampledb?serverTimezone=JST", "root","password");
	          PreparedStatement stmt = conn.prepareStatement(pricesql)){
	          stmt.setString(1, price);
	          try(ResultSet rs=stmt.executeQuery()){
	            while(rs.next()) {

	            // 各種情報をＢｅａｎに保存
	            ShohinBean shohinBean = new ShohinBean();
	            shohinBean.setShohin_code(rs.getString("SHOHIN_CODE"));
	            shohinBean.setShohin_name(rs.getString("SHOHIN_NAME"));
	            shohinBean.setShohin_val(rs.getString("SHOHIN_VOL"));
	            shohinBean.setShohin_price(rs.getInt("SHOHIN_PRICE"));
	            shohinBean.setShohin_area(rs.getString("SHOHIN_AREA"));
	            shohinBean.setShohin_comment(rs.getString("SHOHIN_COMMENT"));
	            shohinBean.setShohin_image(rs.getString("SHOHIN_IMAGE"));
	            shohinArrayBean.addShohinRecordArray(shohinBean);
	            }
	            if(shohinArrayBean.getArraySize()==0) {
	              getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
	            }else {
	            HttpSession session = request.getSession();
	            session.setAttribute("shohinArrayBean", shohinArrayBean);
	            session.setAttribute("radio",radio);
	            session.setAttribute("keyword", price);
	            getServletContext().getRequestDispatcher("/shohin_retrieval.jsp").forward(request, response);
	            }
	          }catch(Exception e){
              e.printStackTrace();
            }
          }catch(Exception e){
            e.printStackTrace();
          }
      }
  }
}

