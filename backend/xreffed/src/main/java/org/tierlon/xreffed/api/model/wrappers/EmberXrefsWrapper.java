package org.tierlon.xreffed.api.model.wrappers;

import org.tierlon.xreffed.api.model.reference.DataReferenceV1;

import java.util.List;

/**
 * Created by justinanddiana on 7/18/14.
 */
public class EmberXrefsWrapper {

    private List<DataReferenceV1> data;

    public EmberXrefsWrapper(List<DataReferenceV1> data) {
        this.data = data;
    }

    public List<DataReferenceV1> getXrefs() {
        return data;
    }
}
