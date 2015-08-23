package zuilib.utils;

import zuilib.core.PositionControler;
import zuilib.core.dimensionControler;
import zuilib.core.zuiAtomObject;

public class areaDimensionControler extends dimensionControler {
  
  public PositionControler position;

  public areaDimensionControler(zuiAtomObject curParent) {
    super(curParent);
    position = new PositionControler(curParent);
  }
  
  public areaDimensionControler(zuiAtomObject curParent, vector start_pos) {
    super(curParent);
    position = new PositionControler(curParent,start_pos);
  }
  
  public areaDimensionControler(zuiAtomObject curParent,vector start_rotpt, vector start_pos) {
    super(curParent,start_rotpt);
    position = new PositionControler(curParent,start_pos);
  }
  
  public areaDimensionControler() {
    super();
    position = new PositionControler();
  }
  
  public areaDimensionControler(vector start_pos) {
    super();
    position = new PositionControler(start_pos);
  }
  
  public areaDimensionControler(vector start_rotpt, vector start_pos) {
    super(start_rotpt);
    position = new PositionControler(start_pos);
  }
  
  public void setup(zuiAtomObject curParent) {
    super.setup(curParent);
    position.setup(curParent);
  }

}
