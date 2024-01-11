package org.bayisehat.rdb2fhir.core.valueservice.extendedtype;

import ca.uhn.fhir.model.primitive.XhtmlDt;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class CustomXhtmlNode extends XhtmlNode {

    @Override
    public String getValueAsString() {
        if (isEmpty()) {
            return null;
        }
        try {
            String retVal = new CustomXhtmlComposer(XhtmlComposer.XML).compose(this);
            retVal = XhtmlDt.preprocessXhtmlNamespaceDeclaration(retVal);
            return retVal;
        } catch (Exception e) {
            // TODO: composer shouldn't throw exception like this
            throw new RuntimeException(e);
        }
    }
}
