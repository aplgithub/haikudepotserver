package org.haiku.haikudepotserver.dataobjects.auto;

import org.apache.cayenne.exp.Property;
import org.haiku.haikudepotserver.dataobjects.support.AbstractDataObject;

/**
 * Class _Country was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Country extends AbstractDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "id";

    public static final Property<Boolean> ACTIVE = Property.create("active", Boolean.class);
    public static final Property<String> CODE = Property.create("code", String.class);
    public static final Property<String> NAME = Property.create("name", String.class);

    public void setActive(boolean active) {
        writeProperty("active", active);
    }
	public boolean isActive() {
        Boolean value = (Boolean)readProperty("active");
        return (value != null) ? value.booleanValue() : false;
    }

    public void setCode(String code) {
        writeProperty("code", code);
    }
    public String getCode() {
        return (String)readProperty("code");
    }

    public void setName(String name) {
        writeProperty("name", name);
    }
    public String getName() {
        return (String)readProperty("name");
    }

}