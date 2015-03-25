
package org.soaplab.malt_parser;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.soaplab.malt_parser package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetResultsResponse_QNAME = new QName("http://soaplab.org/malt_parser", "getResultsResponse");
    private final static QName _RunAndWaitFor_QNAME = new QName("http://soaplab.org/malt_parser", "runAndWaitFor");
    private final static QName _RunAndWaitForResponse_QNAME = new QName("http://soaplab.org/malt_parser", "runAndWaitForResponse");
    private final static QName _Run_QNAME = new QName("http://soaplab.org/malt_parser", "run");
    private final static QName _GetSomeResultsResponse_QNAME = new QName("http://soaplab.org/malt_parser", "getSomeResultsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.soaplab.malt_parser
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RunAndWaitForResponse }
     * 
     */
    public RunAndWaitForResponse createRunAndWaitForResponse() {
        return new RunAndWaitForResponse();
    }

    /**
     * Create an instance of {@link AppResults }
     * 
     */
    public AppResults createAppResults() {
        return new AppResults();
    }

    /**
     * Create an instance of {@link AppInputs }
     * 
     */
    public AppInputs createAppInputs() {
        return new AppInputs();
    }

    /**
     * Create an instance of {@link RunAndWaitFor }
     * 
     */
    public RunAndWaitFor createRunAndWaitFor() {
        return new RunAndWaitFor();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppResults }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaplab.org/malt_parser", name = "getResultsResponse")
    public JAXBElement<AppResults> createGetResultsResponse(AppResults value) {
        return new JAXBElement<AppResults>(_GetResultsResponse_QNAME, AppResults.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RunAndWaitFor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaplab.org/malt_parser", name = "runAndWaitFor")
    public JAXBElement<RunAndWaitFor> createRunAndWaitFor(RunAndWaitFor value) {
        return new JAXBElement<RunAndWaitFor>(_RunAndWaitFor_QNAME, RunAndWaitFor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RunAndWaitForResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaplab.org/malt_parser", name = "runAndWaitForResponse")
    public JAXBElement<RunAndWaitForResponse> createRunAndWaitForResponse(RunAndWaitForResponse value) {
        return new JAXBElement<RunAndWaitForResponse>(_RunAndWaitForResponse_QNAME, RunAndWaitForResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppInputs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaplab.org/malt_parser", name = "run")
    public JAXBElement<AppInputs> createRun(AppInputs value) {
        return new JAXBElement<AppInputs>(_Run_QNAME, AppInputs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppResults }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soaplab.org/malt_parser", name = "getSomeResultsResponse")
    public JAXBElement<AppResults> createGetSomeResultsResponse(AppResults value) {
        return new JAXBElement<AppResults>(_GetSomeResultsResponse_QNAME, AppResults.class, null, value);
    }

}
