package net.umllint.plugin.vp.ui.result;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;
import java.io.IOException;
import java.net.URL;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

class ActivatedHyperlinkListener implements HyperlinkListener {

    JEditorPane editorPane;

    public ActivatedHyperlinkListener(JEditorPane editorPane) {
        this.editorPane = editorPane;
    }

    public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {
        HyperlinkEvent.EventType type = hyperlinkEvent.getEventType();
        final URL url = hyperlinkEvent.getURL();
        if (type == HyperlinkEvent.EventType.ENTERED) {
            System.out.println("URL: " + url);
        } else if (type == HyperlinkEvent.EventType.ACTIVATED) {
            System.out.println("Activated");
            Document doc = editorPane.getDocument();
            try {
                editorPane.setPage(url);
            } catch (IOException ioException) {
                System.out.println("Error following link");
                editorPane.setDocument(doc);
            }
        }
    }
}
