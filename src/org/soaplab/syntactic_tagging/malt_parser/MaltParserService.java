
package org.soaplab.syntactic_tagging.malt_parser;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "malt_parserService", targetNamespace = "http://soaplab.org/syntactic_tagging/malt_parser", wsdlLocation = "http://ws04.iula.upf.edu/soaplab2-axis/typed/services/syntactic_tagging.malt_parser?wsdl")
public class MaltParserService
    extends Service
{

    private final static URL MALTPARSERSERVICE_WSDL_LOCATION;
    private final static WebServiceException MALTPARSERSERVICE_EXCEPTION;
    private final static QName MALTPARSERSERVICE_QNAME = new QName("http://soaplab.org/syntactic_tagging/malt_parser", "malt_parserService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://ws04.iula.upf.edu/soaplab2-axis/typed/services/syntactic_tagging.malt_parser?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MALTPARSERSERVICE_WSDL_LOCATION = url;
        MALTPARSERSERVICE_EXCEPTION = e;
    }

    public MaltParserService() {
        super(__getWsdlLocation(), MALTPARSERSERVICE_QNAME);
    }

    public MaltParserService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MALTPARSERSERVICE_QNAME, features);
    }

    public MaltParserService(URL wsdlLocation) {
        super(wsdlLocation, MALTPARSERSERVICE_QNAME);
    }

    public MaltParserService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MALTPARSERSERVICE_QNAME, features);
    }

    public MaltParserService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MaltParserService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MaltParser
     */
    @WebEndpoint(name = "malt_parserPort")
    public MaltParser getMaltParserPort() {
        return super.getPort(new QName("http://soaplab.org/syntactic_tagging/malt_parser", "malt_parserPort"), MaltParser.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MaltParser
     */
    @WebEndpoint(name = "malt_parserPort")
    public MaltParser getMaltParserPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://soaplab.org/syntactic_tagging/malt_parser", "malt_parserPort"), MaltParser.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MALTPARSERSERVICE_EXCEPTION!= null) {
            throw MALTPARSERSERVICE_EXCEPTION;
        }
        return MALTPARSERSERVICE_WSDL_LOCATION;
    }

}
