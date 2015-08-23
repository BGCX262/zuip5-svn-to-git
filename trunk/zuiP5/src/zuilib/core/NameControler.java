package zuilib.core;

public class NameControler extends propertyObject {
  
  private String name;
  
  public NameControler(zuiAtomObject curParent, String curName) {
    super(curParent);
    name = curName;
  }
  
  public NameControler(String curName) {
    super();
    name = curName;
  }
  
  public NameControler(zuiAtomObject curParent) {
    super(curParent);
    name = "NoName"+Integer.toHexString(hashCode());
  }
  
  public NameControler() {
    super();
    name = "NoName"+Integer.toHexString(hashCode());
  }
  
  public String getName() {
    return name;
  }
  
  public String get() {
    return getName();
  }
  
  public void setName(String curName) {
    name = curName;
  }
  
  public void set(String curName) {
    setName(curName);
  }
  
  public boolean equals(String string) {
    return name.equals(string);
  }

}
