package net.umllint.plugin.vp.actions.setup;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.action.VPContext;

import java.awt.event.ActionEvent;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class SetupActionController implements VPActionController {

    @Override
    public void performAction(VPAction vpAction) {

        ApplicationManager.instance().getViewManager().showMessage("SETUP");

    }

    @Override
    public void update(VPAction vpAction) {

    }
}
