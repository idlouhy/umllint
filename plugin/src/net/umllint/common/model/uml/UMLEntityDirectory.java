package net.umllint.common.model.uml;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A Tool for Checking Correctness of Design Diagrams in UML
 * Ivo Dlouhy, xdlouh05@stud.fit.vutbr.cz
 * http://umllint.net
 * Master's thesis
 * Brno University of Technology, Faculty of Information Technology
 */

public class UMLEntityDirectory {

    private static UMLEntityDirectory instance = null;
    private Map<String, UMLAbstractEntity> map = new HashMap<String, UMLAbstractEntity>();

    protected UMLEntityDirectory() {

    }

    public static UMLEntityDirectory getInstance() {
        if (instance == null) {
            instance = new UMLEntityDirectory();
        }
        return instance;
    }

    public UMLAbstractEntity getElement(String id) {
        return map.get(id);
    }

    public void setElement(UMLAbstractEntity entity) {
        String id = entity.getXmiId();
        if (id != null) {
            map.put(id, entity);
        }
        else {
            System.out.println("DIRECTORY NO ID! "+entity.getName());
        }

    }

    public Collection<UMLAbstractEntity> getEntries() {
        return map.values();
    }


    public void dump() {

        for (UMLAbstractEntity umlAbstractEntity : map.values()) {
            System.out.println(umlAbstractEntity.toString());
        }

    }






    public static String resolveXmiId(String xmiId) {
        UMLAbstractEntity entity = UMLEntityDirectory.getInstance().getElement(xmiId);
        if (entity != null) {
            return String.format("[%s] %s", xmiId, entity.getName());
        }
        else {
            return xmiId;
        }
    }
}
