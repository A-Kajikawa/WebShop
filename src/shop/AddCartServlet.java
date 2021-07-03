package shop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.CartArrayBean;
import Bean.CartBean;


@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    CartBean cartbean = new CartBean();


    //商品コード取得
    String code = request.getParameter("code");
    //数量取得
    int quantity = Integer.parseInt(request.getParameter("quantity"));

  //DB接続
    try{
      Class.forName("com.mysql.jdbc.Driver");
    }catch (Exception e) {
      e.printStackTrace();
    }

    //商品コード検索

    String addsql = "SELECT * FROM SHOHIN WHERE SHOHIN_CODE = ?;";

    try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/s2a1sampledb?serverTimezone=JST", "root","password");
        PreparedStatement stmt = conn.prepareStatement(addsql)){
        stmt.setString(1, code);
        try(ResultSet rs=stmt.executeQuery()){
          if(rs.next()){
          // 各種情報をＢｅａｎに保存
          cartbean.setShohin_code(rs.getString("SHOHIN_CODE"));
          cartbean.setShohin_name(rs.getString("SHOHIN_NAME"));
          cartbean.setShohin_val(rs.getString("SHOHIN_VOL"));
          cartbean.setShohin_price(rs.getInt("SHOHIN_PRICE"));
          cartbean.setShohin_area(rs.getString("SHOHIN_AREA"));
          cartbean.setShohin_comment(rs.getString("SHOHIN_COMMENT"));
          cartbean.setShohin_image(rs.getString("SHOHIN_IMAGE"));
          HttpSession session = request.getSession();
          CartArrayBean cartArrayBean = (CartArrayBean)session.getAttribute("cartArrayBean");

          if (cartArrayBean == null) { //セッション内にカート情報が無い=初回
              cartArrayBean = new CartArrayBean(); //カート情報をnewする

          }
          CartBean del = null;


          for (CartBean rec : cartArrayBean.getCartArray()) {

                      if (rec.getShohin_code().equals(code)) {
                          del = rec;
                          break;
                      }
                  }

                  if (del != null) {//2回目時
                    cartbean.setQuantity(quantity+del.getQuantity());
                    cartArrayBean.getCartArray().remove(del);
                  }else {//1回目時
                    cartbean.setQuantity(quantity);
                  }

                  cartArrayBean.addCartArray(cartbean);
                  session.setAttribute("cartArrayBean", cartArrayBean);//セッションに登録する
          getServletContext().getRequestDispatcher("/shohin_cart.jsp").forward(request, response);
          }else{
            System.out.println("ああああああ");
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
