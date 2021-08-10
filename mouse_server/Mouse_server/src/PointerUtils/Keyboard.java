package PointerUtils;

import DataUtils.QueueItem;
import PointerUtils.DataUtil.*;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;

public class Keyboard {
    Robot robot;
    Clipboard clipboard;
    HashMap<Character, KeyStrokeUtil> keyMap;
    public Keyboard(Robot r) {
        robot = r;
        keyMap = KeyStrokeUtil.keyMap;
        if(keyMap == null) {
            keyMap = new HashMap<>();
        }
        if(keyMap.size() == 0) {
            keyMap.put('!', new KeyStrokeUtil('1', 1));
            keyMap.put('@', new KeyStrokeUtil('2', 1));
            keyMap.put('#', new KeyStrokeUtil('3', 1));
            keyMap.put('$', new KeyStrokeUtil('4', 1));
            keyMap.put('%', new KeyStrokeUtil('5', 1));
            keyMap.put('^', new KeyStrokeUtil('6', 1));
            keyMap.put('&', new KeyStrokeUtil('7', 1));
            keyMap.put('*', new KeyStrokeUtil('8', 1));
            keyMap.put('(', new KeyStrokeUtil('9', 1));
            keyMap.put(')', new KeyStrokeUtil('0', 1));
            keyMap.put('_', new KeyStrokeUtil('-', 1));
            keyMap.put('-', new KeyStrokeUtil('-', 0));
            keyMap.put('+', new KeyStrokeUtil('=', 1));
            keyMap.put('=', new KeyStrokeUtil('=', 0));
            keyMap.put('`', new KeyStrokeUtil(192, 0));
            keyMap.put('~', new KeyStrokeUtil(131, 0));
            keyMap.put('{', new KeyStrokeUtil(161, 0));
            keyMap.put('}', new KeyStrokeUtil(162, 0));
            keyMap.put('[', new KeyStrokeUtil(91, 0));
            keyMap.put(']', new KeyStrokeUtil(93, 0));
            keyMap.put('\\', new KeyStrokeUtil(92, 0));
            keyMap.put('|', new KeyStrokeUtil(92, 1));
            keyMap.put(';', new KeyStrokeUtil(59, 0));
            keyMap.put(':', new KeyStrokeUtil(59, 1));
            keyMap.put('\'', new KeyStrokeUtil(222, 0));
            keyMap.put('\"', new KeyStrokeUtil(222, 1));
            keyMap.put('<', new KeyStrokeUtil(153, 0));
            keyMap.put('>', new KeyStrokeUtil(160, 0));
            keyMap.put(',', new KeyStrokeUtil(44, 0));
            keyMap.put('.', new KeyStrokeUtil(46, 0));
            keyMap.put('/', new KeyStrokeUtil(47, 0));
            keyMap.put('?', new KeyStrokeUtil(47, 1));
            keyMap.put(' ', new KeyStrokeUtil(' ', 0));
        }

        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }
    public void onKeyPressed(QueueItem item) {
        KeyBoardPressData data = (KeyBoardPressData) item.getmObject();
        robot.keyPress(KeyboardPerformKeyActionData.getKeyCode(data.keyType));
    }

    public void onKeyReleased(QueueItem item) {
        KeyBoardReleaseData data = (KeyBoardReleaseData) item.getmObject();
        robot.keyRelease(KeyboardPerformKeyActionData.getKeyCode(data.keyType));
    }

    public void onKeyAction(QueueItem item) {
        KeyboardPerformKeyActionData data = (KeyboardPerformKeyActionData) item.getmObject();
        for(int i = 0; i < data.numPresses; i++) {
            robot.keyPress(KeyboardPerformKeyActionData.getKeyCode(data.keyItem));
            robot.keyRelease(KeyboardPerformKeyActionData.getKeyCode(data.keyItem));
        }
    }

    public void onTextInput(QueueItem item) {
        TextInputData data = (TextInputData) item.getmObject();

        if(!isTypable(data.text)) {
            StringSelection selection = new StringSelection("" +data.text);
            System.out.println("Input text: " + data.text);
            clipboard.setContents(selection, null);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        } else {
            for(int i = 0; i < data.text.length(); i++) {
                char letter = data.text.charAt(i);
                KeyStrokeUtil strokeUtil;
                if(keyMap.containsKey(letter)) {
                    strokeUtil = keyMap.get(letter);
                } else if(letter >= '0' && letter <= '9') {
                    strokeUtil = new KeyStrokeUtil  (letter, 0);
                } else if(Character.isLowerCase(letter)) {
                    strokeUtil = new KeyStrokeUtil(Character.toUpperCase(letter), 0);
                } else {
                    strokeUtil = new KeyStrokeUtil(letter, 1);
                }

                if(strokeUtil.shift == 1) {
                    robot.keyPress(KeyEvent.VK_SHIFT);
                }

                robot.keyPress(strokeUtil.keyCode);
                robot.keyRelease(strokeUtil.keyCode);

                if(strokeUtil.shift == 1) {
                    robot.keyRelease(KeyEvent.VK_SHIFT);
                }
            }
        }
    }

    public boolean isTypable(String data) {
        for(int i = 0; i < data.length(); i++) {
            char letter = data.charAt(i);
            if(!(keyMap.containsKey(letter) || isLetterOrDigit(letter))) {
                return false;
            }
        }
        return true;
    }

    public boolean isLetterOrDigit(char letter) {
        if(letter >= '0' && letter <= '9')
            return true;
        if(letter >= 'A' && letter <= 'Z')
            return true;
        if(letter >= 'a' && letter <= 'z')
            return true;
        return false;
    }
}
