
package org.soaplab.malt_parser;

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
 *         &lt;choice>
 *           &lt;element name="input_direct_data" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="input_url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/choice>
 *         &lt;element name="language">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="es"/>
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
@XmlType(name = "appInputs", propOrder = {
    "inputDirectData",
    "inputUrl",
    "language"
})
@XmlSeeAlso({
    RunAndWaitFor.class
})
public class AppInputs {

    @XmlElement(name = "input_direct_data")
    protected String inputDirectData;
    @XmlElement(name = "input_url")
    protected String inputUrl;
    @XmlElement(required = true)
    protected String language;

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
     * Obtiene el valor de la propiedad language.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Define el valor de la propiedad language.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

}
