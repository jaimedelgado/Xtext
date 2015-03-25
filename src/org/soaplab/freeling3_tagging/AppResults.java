
package org.soaplab.freeling3_tagging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para appResults complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="appResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="report" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="detailed_status" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="output" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="output_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "appResults", propOrder = {
    "report",
    "detailedStatus",
    "output",
    "outputUrl"
})
@XmlSeeAlso({
    RunAndWaitForResponse.class
})
public class AppResults {

    protected String report;
    @XmlElement(name = "detailed_status")
    protected Long detailedStatus;
    protected String output;
    @XmlElement(name = "output_url")
    protected String outputUrl;

    /**
     * Obtiene el valor de la propiedad report.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReport() {
        return report;
    }

    /**
     * Define el valor de la propiedad report.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReport(String value) {
        this.report = value;
    }

    /**
     * Obtiene el valor de la propiedad detailedStatus.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDetailedStatus() {
        return detailedStatus;
    }

    /**
     * Define el valor de la propiedad detailedStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDetailedStatus(Long value) {
        this.detailedStatus = value;
    }

    /**
     * Obtiene el valor de la propiedad output.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutput() {
        return output;
    }

    /**
     * Define el valor de la propiedad output.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutput(String value) {
        this.output = value;
    }

    /**
     * Obtiene el valor de la propiedad outputUrl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputUrl() {
        return outputUrl;
    }

    /**
     * Define el valor de la propiedad outputUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputUrl(String value) {
        this.outputUrl = value;
    }

}
