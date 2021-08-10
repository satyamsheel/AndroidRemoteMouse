package PointerUtils.DataUtil;

public class KeyBoardPressData {
    public int keyType;
    public KeyBoardPressData(String data) {
        String components[] = data.split(",");
        keyType = Integer.parseInt(components[0].trim());
    }
}
