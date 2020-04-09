import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.*;
import java.util.List;

public class AutoCompleteBox extends JComboBox {
    static final long serialVersionUID = 4321421L;
    private final Searchable<String,String> searchable;

    /**
     * Constructs a new object based upon the parameter searchable
     * @param search
     */
    public AutoCompleteBox(Searchable<String,String> search) {
        super();
        this.searchable = search;
        setEditable(true);
        Component component = getEditor().getEditorComponent();
        if(component instanceof JTextComponent) {
            final JTextComponent textComponent = (JTextComponent)component;
            textComponent.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    update();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    update();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {

                }

                public void update() {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            List<String> founds = new ArrayList<>(searchable.search(textComponent.getText()));
                            Set<String> foundSet = new HashSet<String>();
                            for (String s : founds) {
                                foundSet.add(s.toLowerCase());
                            }
                            Collections.sort(founds); //sort alphabetically
                            setEditable(false);
                            removeAllItems();
                            //if founds contains the search text, then only add once.
                            if(!foundSet.contains(textComponent.getText().toLowerCase())) {
                                addItem(textComponent.getText());
                            }
                            for (String s : founds) {
                                addItem(s);
                            }
                            setEditable(true);
                            setPopupVisible(true);
                        }
                    });
                }
            });
            //When the text component changes, focus is gained
            //and the menu disappears. To account for this, whenever the focus
            //is gained by the JTextComponent and it has searchable values, we show the popup.
            textComponent.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    if(textComponent.getText().length() > 0) {
                        setPopupVisible(true);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {

                }
            });
        } else {
            throw new IllegalStateException("Editing component is not a JTextComponent!");
        }
    }
}
