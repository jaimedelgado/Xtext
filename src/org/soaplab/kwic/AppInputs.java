
package org.soaplab.kwic;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para appInputs complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="appInputs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="query" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;choice>
 *           &lt;element name="input_direct_data" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="input_url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/choice>
 *         &lt;element name="windowSize" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="limit" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="sortBy" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="word"/>
 *               &lt;enumeration value="left"/>
 *               &lt;enumeration value="right"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="xml" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "appInputs", propOrder = {
    "query",
    "inputDirectData",
    "inputUrl",
    "windowSize",
    "limit",
    "sortBy",
    "xml"
})
@XmlSeeAlso({
    RunAndWaitFor.class
})
public class AppInputs {

    @XmlElement(required = true)
    protected String query;
    @XmlElement(name = "input_direct_data")
    protected String inputDirectData;
    @XmlElement(name = "input_url")
    protected String inputUrl;
    protected Long windowSize;
    protected Long limit;
    protected String sortBy;
    @XmlElement(defaultValue = "false")
    protected Boolean xml;

    /**
     * Obtiene el valor de la propiedad query.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuery() {
        return query;
    }

    /**
     * Define el valor de la propiedad query.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuery(String value) {
        this.query = value;
    }

    /**
     * Obtiene el valor de la propiedad inputDirectData.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputDirectData() {
        return inputDirectData;
    }

    /**
     * Define el valor de la propiedad inputDirectData.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputDirectData(String value) {
        this.inputDirectData = value;
    }

    /**
     * Obtiene el valor de la propiedad inputUrl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputUrl() {
        return inputUrl;
    }

    /**
     * Define el valor de la propiedad inputUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputUrl(String value) {
        this.inputUrl = value;
    }

    /**
     * Obtiene el valor de la propiedad windowSize.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getWindowSize() {
        return windowSize;
    }

    /**
     * Define el valor de la propiedad windowSize.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setWindowSize(Long value) {
        this.windowSize = value;
    }

    /**
     * Obtiene el valor de la propiedad limit.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * Define el valor de la propiedad limit.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLimit(Long value) {
        this.limit = value;
    }

    /**
     * Obtiene el valor de la propiedad sortBy.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * Define el valor de la propiedad sortBy.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortBy(String value) {
        this.sortBy = value;
    }

    /**
     * Obtiene el valor de la propiedad xml.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isXml() {
        return xml;
    }

    /**
     * Define el valor de la propiedad xml.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setXml(Boolean value) {
        this.xml = value;
    }

}
