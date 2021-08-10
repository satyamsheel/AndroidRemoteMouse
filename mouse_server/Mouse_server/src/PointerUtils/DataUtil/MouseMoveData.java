package PointerUtils.DataUtil;

public class MouseMoveData {
    public float xVelocity;
    public float yVelocity;
    public int scale;

    public MouseMoveData(String data) {
        String components[] = data.split(",");
        xVelocity = Float.parseFloat(components[0]);
        yVelocity = Float.parseFloat(components[1]);
        double velocity = Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
        scale = Integer.parseInt(components[2].trim());
    }
}
