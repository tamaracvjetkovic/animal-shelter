package util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

public class UIUtils {

    private UIUtils() {}

    // centers the component in the centre of the desktop
    public static void center(Component component) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = component.getSize().width;
        int h = component.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        component.setLocation(x, y);
    }
}
