
package com.babc.server.web.soap.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.babc.server.web.soap.entity.Story;

@XmlRootElement(name = "getRelatedStoriesResponse", namespace = "http://soap.web.server.babc.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRelatedStoriesResponse", namespace = "http://soap.web.server.babc.com/")
public class GetRelatedStoriesResponse {

    @XmlElement(name = "return", namespace = "")
    private List<Story> _return;

    /**
     * 
     * @return
     *     returns List<Story>
     */
    public List<Story> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<Story> _return) {
        this._return = _return;
    }

}
