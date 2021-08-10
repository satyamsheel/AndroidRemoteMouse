package PointerUtils.DataUtil;

import PointerUtils.Keyboard;

import java.awt.event.KeyEvent;

public class KeyboardPerformKeyActionData {
    public int keyItem;
    public int numPresses;
    public KeyboardPerformKeyActionData(String data) {
        String[] components = data.split(",");
        keyItem = Integer.parseInt(components[0].trim());
        try {
            numPresses = Integer.parseInt(components[1].trim());
        } catch (Exception e) {
            numPresses = 1;
        }
    }
    public static int getKeyCode(int keyItem) {
        if(keyItem == KeyboardEvent.BKSP)
            return KeyEvent.VK_BACK_SPACE;
        if(keyItem == KeyboardEvent.SPACE)
            return KeyEvent.VK_SPACE;
        if(keyItem == KeyboardEvent.TAB)
            return KeyEvent.VK_TAB;
        if(keyItem == KeyboardEvent.DELETE)
            return KeyEvent.VK_DELETE;
        if(keyItem == KeyboardEvent.INSERT)
            return KeyEvent.VK_INSERT;
        if(keyItem == KeyboardEvent.ESC)
            return KeyEvent.VK_ESCAPE;
        if(keyItem == KeyboardEvent.ENTER)
            return KeyEvent.VK_ENTER;
        if(keyItem == KeyboardEvent.UP)
            return KeyEvent.VK_UP;
        if(keyItem == KeyboardEvent.DOWN)
            return KeyEvent.VK_DOWN;
        if(keyItem == KeyboardEvent.LEFT)
            return KeyEvent.VK_LEFT;
        if(keyItem == KeyboardEvent.RIGHT)
            return KeyEvent.VK_RIGHT;
        if(keyItem == KeyboardEvent.F1)
            return KeyEvent.VK_F1;
        if(keyItem == KeyboardEvent.F2)
            return KeyEvent.VK_F2;
        if(keyItem == KeyboardEvent.F3)
            return KeyEvent.VK_F3;
        if(keyItem == KeyboardEvent.F4)
            return KeyEvent.VK_F4;
        if(keyItem == KeyboardEvent.F5)
            return KeyEvent.VK_F5;
        if(keyItem == KeyboardEvent.F6)
            return KeyEvent.VK_F6;
        if(keyItem == KeyboardEvent.F7)
            return KeyEvent.VK_F7;
        if(keyItem == KeyboardEvent.F8)
            return KeyEvent.VK_F8;
        if(keyItem == KeyboardEvent.F9)
            return KeyEvent.VK_F9;
        if(keyItem == KeyboardEvent.F10)
            return KeyEvent.VK_F10;
        if(keyItem == KeyboardEvent.F11)
            return KeyEvent.VK_F11;
        if(keyItem == KeyboardEvent.F12)
            return KeyEvent.VK_F12;
        if(keyItem >= 'A' && keyItem <= 'Z')
            return keyItem;
        if(keyItem >= '0' && keyItem <= '9')
            return keyItem;
        if(keyItem == KeyboardEvent.WIN)
            return KeyEvent.VK_WINDOWS;
        if(keyItem == KeyboardEvent.SHIFT)
            return KeyEvent.VK_SHIFT;
        if(keyItem == KeyboardEvent.ALT)
            return KeyEvent.VK_ALT;
        if(keyItem == KeyboardEvent.CTRL)
            return KeyEvent.VK_CONTROL;
        return -1;
    }
}
