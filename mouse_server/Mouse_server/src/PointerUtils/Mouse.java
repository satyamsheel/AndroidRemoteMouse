package PointerUtils;

import DataUtils.QueueItem;
import PointerUtils.DataUtil.MouseMoveData;
import PointerUtils.DataUtil.MouseScrollData;

import java.awt.*;
import java.awt.event.InputEvent;

public class Mouse {
    Robot robot;

    public Mouse(Robot r) {
        robot = r;
    }

    public void moveMouse(QueueItem item) {
        Point currentPosition = MouseInfo.getPointerInfo().getLocation();
        float xVelocity, yVelocity;
        int scale;
        MouseMoveData point = (MouseMoveData) item.getObject();
        xVelocity = point.xVelocity;
        yVelocity = point.yVelocity;
        scale = point.scale;

        int x = Math.round(currentPosition.x + xVelocity * scale);
        int y = Math.round(currentPosition.y + yVelocity * scale);

        robot.mouseMove(x, y);
    }

    public void clickMouse() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void leftPressMouse() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void leftReleaseMouse() {
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void scrollMouse(QueueItem item) {
        MouseScrollData data = (MouseScrollData) item.getmObject();
        float x = data.xVelocity;
        float y = data.yVelocity;
        int multiplier = data.multiplier;
        int scrollValue = (int) (multiplier * Math.signum(y));

        robot.mouseWheel(scrollValue);
    }

    public void rightClickMouse() {
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    public void rightPressMouse() {
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
    }

    public void rightReleaseMouse() {
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }
}
