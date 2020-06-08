package CharacterCreator;

import javax.swing.*;
import java.awt.*;
import java.net.URLDecoder;

public class CustomComboBoxRenderer extends JLabel implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {

        ImageIcon icon = new ImageIcon( "resources/" + value.toString());

        setIcon(icon);
        setText(value.toString());

        return this;
    }
}
