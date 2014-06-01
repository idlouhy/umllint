package net.umllint.plugin.vp.actions.homepage;

import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class HomepageActionController implements VPActionController {

    @Override
    public void performAction(VPAction vpAction) {

        if(Desktop.isDesktopSupported())
        {
            try {
                Desktop.getDesktop().browse(new URI("http://umllint.net"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(VPAction vpAction) {

    }
}
