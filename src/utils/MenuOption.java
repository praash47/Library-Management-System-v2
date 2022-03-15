package utils;
import java.awt.Color;
import java.util.*;
import javax.swing.*;

public class MenuOption {
	public Hashtable options = new Hashtable();
	public MenuOption(String optionName, Color color, ImageIcon icon) {
		options.put("name", optionName);
		options.put("color", color);
		options.put("icon", icon);
	};
}
