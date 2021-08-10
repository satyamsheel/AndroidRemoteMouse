package PointerUtils.DataUtil;

public class LogData {
    public int logType;
    public String logString;
    public LogData(String data) {
        String[] comps = data.split(",");
        logType = Integer.parseInt(comps[0].trim());
        logString = data.substring(data.indexOf(",") + 1);
    }
}
