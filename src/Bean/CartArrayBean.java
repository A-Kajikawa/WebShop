package Bean;

import java.io.Serializable;
import java.util.ArrayList;

public class CartArrayBean implements Serializable {

  public CartArrayBean() {
  }

  ArrayList<CartBean> cartArray = new ArrayList<CartBean>();
  //累計メソッド
  public int getTotalAllPrice() {
    int sum = 0;
    for (CartBean rec : cartArray) {
      sum += rec.getTotalPrice();
          }
    return sum;
  }

  public ArrayList<CartBean> getCartArray() {
    return cartArray;
  }

  public void addCartArray(CartBean obj) {
    cartArray.add(obj);
  }

  public void delCartArray(String shohin_code) {
    CartBean del = null;
  for (CartBean rec : cartArray) {
              if (rec.getShohin_code().equals(shohin_code)) {
                  del = rec; //ここで削除するとループ中に要素数が変わり例外になる可能性あり
                  break;
              }
          }
          if (del != null) {
            cartArray.remove(del); //ループの外で削除すれば安全
          }
  }

}
