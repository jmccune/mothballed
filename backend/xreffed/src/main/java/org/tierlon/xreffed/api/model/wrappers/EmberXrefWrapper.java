package org.tierlon.xreffed.api.model.wrappers;

import org.tierlon.xreffed.api.model.reference.DataReferenceV1;

/**
 * Created by justinanddiana on 7/24/14.
 */
public class EmberXrefWrapper {
    private DataReferenceV1 xref;

    public EmberXrefWrapper() {xref=null;}
    public EmberXrefWrapper(DataReferenceV1 xref) {
        this.xref = xref;
    }

    public void setXref(DataReferenceV1 xref) {
        this.xref = xref;
    }

    public DataReferenceV1 getXref() {
        return xref;
    }

}
