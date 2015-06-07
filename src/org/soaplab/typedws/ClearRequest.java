
package org.soaplab.typedws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para clearRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="clearRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="jobId" type="{http://soaplab.org/typedws}jobId"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "clearRequest", propOrder = {
    "jobId"
})
public class ClearRequest {

    @XmlElement(required = true)
    protected JobId jobId;

    /**
     * Obtiene el valor de la propiedad jobId.
     * 
     * @return
     *     possible object is
     *     {@link JobId }
     *     
     */
    public JobId getJobId() {
        return jobId;
    }

    /**
     * Define el valor de la propiedad jobId.
     * 
     * @param value
     *     allowed object is
     *     {@link JobId }
     *     
     */
    public void setJobId(JobId value) {
        this.jobId = value;
    }

}
