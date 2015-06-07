
package org.soaplab.freeling3_tagging;

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
 *               &lt;enumeration value="en"/>
 *               &lt;enumeration value="ca"/>
 *               &lt;enumeration value="es"/>
 *               &lt;enumeration value="old-es"/>
 *               &lt;enumeration value="ast"/>
 *               &lt;enumeration value="cy"/>
 *               &lt;enumeration value="gl"/>
 *               &lt;enumeration value="it"/>
 *               &lt;enumeration value="ru"/>
 *               &lt;enumeration value="pt"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="keeptags" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="noner" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="bio" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nonec" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flush" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="noafx" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="noloc" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nonumb" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nopunt" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nodate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="noquant" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nodict" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="noprob" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="xmlcqp" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "language",
    "keeptags",
    "noner",
    "bio",
    "nonec",
    "flush",
    "noafx",
    "noloc",
    "nonumb",
    "nopunt",
    "nodate",
    "noquant",
    "nodict",
    "noprob",
    "xmlcqp"
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
    @XmlElement(defaultValue = "false")
    protected Boolean keeptags;
    @XmlElement(defaultValue = "false")
    protected Boolean noner;
    @XmlElement(defaultValue = "false")
    protected Boolean bio;
    @XmlElement(defaultValue = "false")
    protected Boolean nonec;
    @XmlElement(defaultValue = "false")
    protected Boolean flush;
    @XmlElement(defaultValue = "false")
    protected Boolean noafx;
    @XmlElement(defaultValue = "false")
    protected Boolean noloc;
    @XmlElement(defaultValue = "false")
    protected Boolean nonumb;
    @XmlElement(defaultValue = "false")
    protected Boolean nopunt;
    @XmlElement(defaultValue = "false")
    protected Boolean nodate;
    @XmlElement(defaultValue = "false")
    protected Boolean noquant;
    @XmlElement(defaultValue = "false")
    protected Boolean nodict;
    @XmlElement(defaultValue = "false")
    protected Boolean noprob;
    @XmlElement(defaultValue = "false")
    protected Boolean xmlcqp;

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

    /**
     * Obtiene el valor de la propiedad keeptags.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isKeeptags() {
        return keeptags;
    }

    /**
     * Define el valor de la propiedad keeptags.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setKeeptags(Boolean value) {
        this.keeptags = value;
    }

    /**
     * Obtiene el valor de la propiedad noner.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoner() {
        return noner;
    }

    /**
     * Define el valor de la propiedad noner.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoner(Boolean value) {
        this.noner = value;
    }

    /**
     * Obtiene el valor de la propiedad bio.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBio() {
        return bio;
    }

    /**
     * Define el valor de la propiedad bio.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBio(Boolean value) {
        this.bio = value;
    }

    /**
     * Obtiene el valor de la propiedad nonec.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNonec() {
        return nonec;
    }

    /**
     * Define el valor de la propiedad nonec.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonec(Boolean value) {
        this.nonec = value;
    }

    /**
     * Obtiene el valor de la propiedad flush.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlush() {
        return flush;
    }

    /**
     * Define el valor de la propiedad flush.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlush(Boolean value) {
        this.flush = value;
    }

    /**
     * Obtiene el valor de la propiedad noafx.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoafx() {
        return noafx;
    }

    /**
     * Define el valor de la propiedad noafx.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoafx(Boolean value) {
        this.noafx = value;
    }

    /**
     * Obtiene el valor de la propiedad noloc.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoloc() {
        return noloc;
    }

    /**
     * Define el valor de la propiedad noloc.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoloc(Boolean value) {
        this.noloc = value;
    }

    /**
     * Obtiene el valor de la propiedad nonumb.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNonumb() {
        return nonumb;
    }

    /**
     * Define el valor de la propiedad nonumb.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonumb(Boolean value) {
        this.nonumb = value;
    }

    /**
     * Obtiene el valor de la propiedad nopunt.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNopunt() {
        return nopunt;
    }

    /**
     * Define el valor de la propiedad nopunt.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNopunt(Boolean value) {
        this.nopunt = value;
    }

    /**
     * Obtiene el valor de la propiedad nodate.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNodate() {
        return nodate;
    }

    /**
     * Define el valor de la propiedad nodate.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNodate(Boolean value) {
        this.nodate = value;
    }

    /**
     * Obtiene el valor de la propiedad noquant.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoquant() {
        return noquant;
    }

    /**
     * Define el valor de la propiedad noquant.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoquant(Boolean value) {
        this.noquant = value;
    }

    /**
     * Obtiene el valor de la propiedad nodict.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNodict() {
        return nodict;
    }

    /**
     * Define el valor de la propiedad nodict.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNodict(Boolean value) {
        this.nodict = value;
    }

    /**
     * Obtiene el valor de la propiedad noprob.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoprob() {
        return noprob;
    }

    /**
     * Define el valor de la propiedad noprob.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoprob(Boolean value) {
        this.noprob = value;
    }

    /**
     * Obtiene el valor de la propiedad xmlcqp.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isXmlcqp() {
        return xmlcqp;
    }

    /**
     * Define el valor de la propiedad xmlcqp.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setXmlcqp(Boolean value) {
        this.xmlcqp = value;
    }

}
