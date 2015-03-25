
package org.emboss.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Common sequence input type for EMBOSS
 * 			
 * 
 * <p>Clase Java para sequenceInput complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="sequenceInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="direct_data" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="usa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/choice>
 *         &lt;element name="sformat" type="{http://emboss.org/common}SequenceFormat" minOccurs="0"/>
 *         &lt;element name="sbegin" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="send" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="sprotein" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="snucleotide" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="sreverse" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="slower" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="supper" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sequenceInput", propOrder = {
    "directData",
    "usa",
    "sformat",
    "sbegin",
    "send",
    "sprotein",
    "snucleotide",
    "sreverse",
    "slower",
    "supper"
})
public class SequenceInput {

    @XmlElement(name = "direct_data")
    protected String directData;
    protected String usa;
    @XmlSchemaType(name = "string")
    protected SequenceFormat sformat;
    protected Integer sbegin;
    protected Integer send;
    protected Boolean sprotein;
    protected Boolean snucleotide;
    protected Boolean sreverse;
    protected Boolean slower;
    protected Boolean supper;

    /**
     * Obtiene el valor de la propiedad directData.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectData() {
        return directData;
    }

    /**
     * Define el valor de la propiedad directData.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectData(String value) {
        this.directData = value;
    }

    /**
     * Obtiene el valor de la propiedad usa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsa() {
        return usa;
    }

    /**
     * Define el valor de la propiedad usa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsa(String value) {
        this.usa = value;
    }

    /**
     * Obtiene el valor de la propiedad sformat.
     * 
     * @return
     *     possible object is
     *     {@link SequenceFormat }
     *     
     */
    public SequenceFormat getSformat() {
        return sformat;
    }

    /**
     * Define el valor de la propiedad sformat.
     * 
     * @param value
     *     allowed object is
     *     {@link SequenceFormat }
     *     
     */
    public void setSformat(SequenceFormat value) {
        this.sformat = value;
    }

    /**
     * Obtiene el valor de la propiedad sbegin.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSbegin() {
        return sbegin;
    }

    /**
     * Define el valor de la propiedad sbegin.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSbegin(Integer value) {
        this.sbegin = value;
    }

    /**
     * Obtiene el valor de la propiedad send.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSend() {
        return send;
    }

    /**
     * Define el valor de la propiedad send.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSend(Integer value) {
        this.send = value;
    }

    /**
     * Obtiene el valor de la propiedad sprotein.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSprotein() {
        return sprotein;
    }

    /**
     * Define el valor de la propiedad sprotein.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSprotein(Boolean value) {
        this.sprotein = value;
    }

    /**
     * Obtiene el valor de la propiedad snucleotide.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSnucleotide() {
        return snucleotide;
    }

    /**
     * Define el valor de la propiedad snucleotide.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSnucleotide(Boolean value) {
        this.snucleotide = value;
    }

    /**
     * Obtiene el valor de la propiedad sreverse.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSreverse() {
        return sreverse;
    }

    /**
     * Define el valor de la propiedad sreverse.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSreverse(Boolean value) {
        this.sreverse = value;
    }

    /**
     * Obtiene el valor de la propiedad slower.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSlower() {
        return slower;
    }

    /**
     * Define el valor de la propiedad slower.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSlower(Boolean value) {
        this.slower = value;
    }

    /**
     * Obtiene el valor de la propiedad supper.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSupper() {
        return supper;
    }

    /**
     * Define el valor de la propiedad supper.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSupper(Boolean value) {
        this.supper = value;
    }

}
