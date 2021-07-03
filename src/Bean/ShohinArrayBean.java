package Bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ShohinArrayBean implements Serializable {

  //帯域変数に配列を宣言
  private ArrayList<ShohinBean>shohinRecordArray = new ArrayList<ShohinBean>();
  
  public ShohinArrayBean() {
  }

  public ArrayList<ShohinBean> getShohinRecordArray() {
    return shohinRecordArray;
  }

  public void addShohinRecordArray(ShohinBean obj) {
    shohinRecordArray.add(obj);
  }
  public int getArraySize(){
    return shohinRecordArray.size();
  }
}