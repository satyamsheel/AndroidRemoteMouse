package PointerUtils.DataUtil;

public class MouseScrollData {
    public float xVelocity;
    public float yVelocity;
    public int multiplier;
    public MouseScrollData(String data) {
        String[] components = data.split(",");
        xVelocity = Float.parseFloat(components[0]);
        yVelocity = Float.parseFloat(components[1]);
        multiplier = Integer.parseInt(components[2]);
    }
}
