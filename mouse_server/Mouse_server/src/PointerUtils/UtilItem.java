package PointerUtils;

import DataUtils.QueueItem;
import PointerUtils.DataUtil.*;

public class UtilItem extends QueueItem {
    public  UtilItem(Object obj, long ts, int type) {
        super(obj, ts, type);
    }
    public UtilItem(String data) {
        String components[] = data.split(",");
        long ts = Long.parseLong(components[0]);
        int type = Integer.parseInt(components[1].trim());

        String remStr = "";
        for(int i = 2; i < components.length - 1; i++) {
            remStr += components[i].trim() + ",";
        }
        remStr += components[components.length - 1].trim();

        Object obj = null;
        if(type == DataInfo.MOUSE_MOVE)
            obj = new MouseMoveData(remStr);
        else if(type == DataInfo.MOUSE_CLICK)
            obj = new MouseClickData(ts);
        else if(type == DataInfo.MOUSE_SCROLL)
            obj = new MouseScrollData(remStr);
        else if(type == DataInfo.MOUSE_RIGHT_CLICK)
            obj = new MouseRightClickData();
        else if(type == DataInfo.KEY_PRESSED)
            obj = new KeyBoardPressData(remStr);
        else if(type == DataInfo.KEY_RELEASED)
            obj = new KeyBoardReleaseData(remStr);
        else if(type == DataInfo.TEXT_INPUT)
            obj = new TextInputData(remStr);
        else if(type == DataInfo.KEY_ACTION)
            obj = new KeyboardPerformKeyActionData(remStr);
        else if(type == DataInfo.BROADCAST_MESSAGE)
            obj = new BroadcastMessageData();
        else if(type == DataInfo.LOG)
            obj = new LogData(remStr);

        setmObject(obj);
        setTimestamp(ts);
        setType(type);
    }
}
