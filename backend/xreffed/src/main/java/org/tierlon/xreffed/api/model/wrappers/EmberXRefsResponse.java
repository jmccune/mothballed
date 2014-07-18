package org.tierlon.xreffed.api.model.wrappers;

import org.tierlon.xreffed.api.model.DataReferenceV1;

import java.util.List;

/**
 * Created by justinanddiana on 7/18/14.
 */
public class EmberXRefsResponse {

    private List<DataReferenceV1> data;

    public EmberXRefsResponse(List<DataReferenceV1> data) {
        this.data = data;
    }

    public List<DataReferenceV1> getXrefs() {
        return data;
    }
}
