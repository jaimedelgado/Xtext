
package org.soaplab.typedws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para jobStatus complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="jobStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="jobStatus">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="UNKNOWN"/>
 *               &lt;enumeration value="CREATED"/>
 *               &lt;enumeration value="RUNNING"/>
 *               &lt;enumeration value="COMPLETED"/>
 *               &lt;enumeration value="TERMINATED_BY_REQUEST"/>
 *               &lt;enumeration value="TERMINATED_BY_ERROR"/>
 *               &lt;enumeration value="REMOVED"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "jobStatus", propOrder = {
    "jobStatus"
})
public class JobStatus {

    @XmlElement(required = true)
    protected String jobStatus;

    /**
     * Obtiene el valor de la propiedad jobStatus.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobStatus() {
        return jobStatus;
    }

    /**
     * Define el valor de la propiedad jobStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobStatus(String value) {
        this.jobStatus = value;
    }

}
