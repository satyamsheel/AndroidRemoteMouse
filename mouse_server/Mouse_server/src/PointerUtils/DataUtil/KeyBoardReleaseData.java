package PointerUtils.DataUtil;

public class KeyBoardReleaseData {
    public int keyType;
    public KeyBoardReleaseData(String data) {
        String[] components = data.split(",");
        keyType = Integer.parseInt(components[0].trim());
    }
}
