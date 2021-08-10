package PointerUtils.DataUtil;

import java.util.HashMap;

public class KeyStrokeUtil {
    public int keyCode, shift;
    public static HashMap<Character, KeyStrokeUtil> keyMap;
    public KeyStrokeUtil(int keyCode, int shift) {
        this.keyCode = keyCode;
        this.shift = shift;
    }
}

