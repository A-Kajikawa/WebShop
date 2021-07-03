package Bean;

import java.io.Serializable;

public class CartBean extends ShohinBean implements Serializable{

  public CartBean() {
  }

  //数量
  private int quantity;

  public int getQuantity() {
    return quantity;
  }
  public void setQuantity(int quantity) {
    if(this.quantity == 0){
    this.quantity = quantity;
    }else{
      quantity += quantity;
    }
  }
  //合計メソッド
  public int getTotalPrice() {
    return shohin_price * quantity;
  }

}
