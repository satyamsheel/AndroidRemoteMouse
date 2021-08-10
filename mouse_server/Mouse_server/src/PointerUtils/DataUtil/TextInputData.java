package PointerUtils.DataUtil;

public class TextInputData {
    public String text;
    public TextInputData(String data) {
        String comps[] = data.split(",");
        String str = comps[0];
        text = str.substring(1, str.length() - 1);
    }
}
