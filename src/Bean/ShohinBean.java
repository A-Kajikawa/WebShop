package Bean;

import java.io.Serializable;

public class ShohinBean implements Serializable {

  public ShohinBean() {
  }
  protected String shohin_code;
  protected String shohin_name;
  protected String shohin_val;
  protected int shohin_price;
  protected String shohin_area;
  protected String shohin_comment;
  protected String shohin_image;
  //商品コード
  public String getShohin_code() {
    return shohin_code;
  }
  public void setShohin_code(String shohin_code) {
    this.shohin_code = shohin_code;
  }
  //商品名
  public String getShohin_name() {
    return shohin_name;
  }
  public void setShohin_name(String shohin_name) {
    this.shohin_name = shohin_name;
  }
  //容量
  public String getShohin_val() {
    return shohin_val;
  }
  public void setShohin_val(String shohin_val) {
    this.shohin_val = shohin_val;
  }
  //価格
  public int getShohin_price() {
    return shohin_price;
  }
  public void setShohin_price(int shohin_price) {
    this.shohin_price = shohin_price;
  }
  //販売エリア
  public String getShohin_area() {
    return shohin_area;
  }
  public void setShohin_area(String shohin_area) {
    this.shohin_area = shohin_area;
  }
  //商品セールスコメント
  public String getShohin_comment() {
    return shohin_comment;
  }
  public void setShohin_comment(String shohin_comment) {
    this.shohin_comment = shohin_comment;
  }
  //イメージ
  public String getShohin_image() {
    return shohin_image;
  }
  public void setShohin_image(String shohin_image) {
    this.shohin_image = shohin_image;
  }
}
