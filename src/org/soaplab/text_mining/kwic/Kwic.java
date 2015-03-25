
package org.soaplab.text_mining.kwic;

import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import org.soaplab.typedws.JobId;
import org.soaplab.typedws.ResultInfo;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "kwic", targetNamespace = "http://soaplab.org/text_mining/kwic")
@XmlSeeAlso({
    org.emboss.common.ObjectFactory.class,
    org.soaplab.kwic.ObjectFactory.class,
    org.soaplab.typedws.ObjectFactory.class
})
public interface Kwic {


    /**
     * starts a job and returns its job identifier
     * 
     * @param windowSize
     * @param xml
     * @param query
     * @param limit
     * @param sortBy
     * @param inputUrl
     * @param inputDirectData
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "run")
    @WebResult(name = "jobId", targetNamespace = "")
    @RequestWrapper(localName = "run", targetNamespace = "http://soaplab.org/kwic", className = "org.soaplab.kwic.AppInputs")
    @ResponseWrapper(localName = "runResponse", targetNamespace = "http://soaplab.org/typedws", className = "org.soaplab.typedws.RunResponse")
    public String run(
        @WebParam(name = "query", targetNamespace = "")
        String query,
        @WebParam(name = "input_direct_data", targetNamespace = "")
        String inputDirectData,
        @WebParam(name = "input_url", targetNamespace = "")
        String inputUrl,
        @WebParam(name = "windowSize", targetNamespace = "")
        Long windowSize,
        @WebParam(name = "limit", targetNamespace = "")
        Long limit,
        @WebParam(name = "sortBy", targetNamespace = "")
        String sortBy,
        @WebParam(name = "xml", targetNamespace = "")
        Boolean xml);

    /**
     * starts a job, waits until it is completed and
     * 				returns the job results
     * 
     * @param output
     * @param outputUrl
     * @param windowSize
     * @param detailedStatus
     * @param xml
     * @param query
     * @param limit
     * @param report
     * @param sortBy
     * @param inputUrl
     * @param inputDirectData
     */
    @WebMethod(action = "runAndWaitFor")
    @RequestWrapper(localName = "runAndWaitFor", targetNamespace = "http://soaplab.org/kwic", className = "org.soaplab.kwic.RunAndWaitFor")
    @ResponseWrapper(localName = "runAndWaitForResponse", targetNamespace = "http://soaplab.org/kwic", className = "org.soaplab.kwic.RunAndWaitForResponse")
    public void runAndWaitFor(
        @WebParam(name = "query", targetNamespace = "")
        String query,
        @WebParam(name = "input_direct_data", targetNamespace = "")
        String inputDirectData,
        @WebParam(name = "input_url", targetNamespace = "")
        String inputUrl,
        @WebParam(name = "windowSize", targetNamespace = "")
        Long windowSize,
        @WebParam(name = "limit", targetNamespace = "")
        Long limit,
        @WebParam(name = "sortBy", targetNamespace = "")
        String sortBy,
        @WebParam(name = "xml", targetNamespace = "")
        Boolean xml,
        @WebParam(name = "report", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> report,
        @WebParam(name = "detailed_status", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<Long> detailedStatus,
        @WebParam(name = "output", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> output,
        @WebParam(name = "output_url", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> outputUrl);

    /**
     * returns status of a given job
     * 
     * @param jobId
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "getStatus")
    @WebResult(name = "jobStatus", targetNamespace = "")
    @RequestWrapper(localName = "getStatus", targetNamespace = "http://soaplab.org/typedws", className = "org.soaplab.typedws.GetStatusRequest")
    @ResponseWrapper(localName = "getStatusResponse", targetNamespace = "http://soaplab.org/typedws", className = "org.soaplab.typedws.JobStatus")
    public String getStatus(
        @WebParam(name = "jobId", targetNamespace = "")
        JobId jobId);

    /**
     * terminates the specified job
     * 
     * @param jobId
     */
    @WebMethod(action = "terminate")
    @Oneway
    @RequestWrapper(localName = "terminate", targetNamespace = "http://soaplab.org/typedws", className = "org.soaplab.typedws.TerminateRequest")
    public void terminate(
        @WebParam(name = "jobId", targetNamespace = "")
        JobId jobId);

    /**
     * informs server that resources for the specified job can be cleared
     * 
     * @param jobId
     */
    @WebMethod(action = "clear")
    @Oneway
    @RequestWrapper(localName = "clear", targetNamespace = "http://soaplab.org/typedws", className = "org.soaplab.typedws.ClearRequest")
    public void clear(
        @WebParam(name = "jobId", targetNamespace = "")
        JobId jobId);

    /**
     * waits until the specified job terminates
     * 
     * @param jobId
     */
    @WebMethod(action = "waitfor")
    @Oneway
    @RequestWrapper(localName = "waitfor", targetNamespace = "http://soaplab.org/typedws", className = "org.soaplab.typedws.WaitforRequest")
    public void waitfor(
        @WebParam(name = "jobId", targetNamespace = "")
        JobId jobId);

    /**
     * returns all results of a given job
     * 
     * @param output
     * @param outputUrl
     * @param jobId
     * @param detailedStatus
     * @param report
     */
    @WebMethod(action = "getResults")
    @RequestWrapper(localName = "getResults", targetNamespace = "http://soaplab.org/typedws", className = "org.soaplab.typedws.GetResultsRequest")
    @ResponseWrapper(localName = "getResultsResponse", targetNamespace = "http://soaplab.org/kwic", className = "org.soaplab.kwic.AppResults")
    public void getResults(
        @WebParam(name = "jobId", targetNamespace = "")
        JobId jobId,
        @WebParam(name = "report", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> report,
        @WebParam(name = "detailed_status", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<Long> detailedStatus,
        @WebParam(name = "output", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> output,
        @WebParam(name = "output_url", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> outputUrl);

    /**
     * returns all results of a given job
     * 
     * @param output
     * @param outputUrl
     * @param jobId
     * @param detailedStatus
     * @param report
     * @param resultName
     */
    @WebMethod(action = "getSomeResults")
    @RequestWrapper(localName = "getSomeResults", targetNamespace = "http://soaplab.org/typedws", className = "org.soaplab.typedws.GetSomeResultsRequest")
    @ResponseWrapper(localName = "getSomeResultsResponse", targetNamespace = "http://soaplab.org/kwic", className = "org.soaplab.kwic.AppResults")
    public void getSomeResults(
        @WebParam(name = "jobId", targetNamespace = "")
        JobId jobId,
        @WebParam(name = "resultName", targetNamespace = "")
        List<String> resultName,
        @WebParam(name = "report", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> report,
        @WebParam(name = "detailed_status", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<Long> detailedStatus,
        @WebParam(name = "output", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> output,
        @WebParam(name = "output_url", targetNamespace = "", mode = WebParam.Mode.OUT)
        Holder<String> outputUrl);

    /**
     * returns a structured description of the service, in xml
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "describe")
    @WebResult(name = "description", targetNamespace = "")
    @RequestWrapper(localName = "describe", targetNamespace = "http://soaplab.org/typedws", className = "org.soaplab.typedws.DescribeRequest")
    @ResponseWrapper(localName = "describeResponse", targetNamespace = "http://soaplab.org/typedws", className = "org.soaplab.typedws.DescribeResponse")
    public String describe();

    /**
     * 
     * @param jobId
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "getLastEvent")
    @WebResult(name = "event", targetNamespace = "")
    @RequestWrapper(localName = "getLastEvent", targetNamespace = "http://soaplab.org/typedws", className = "org.soaplab.typedws.GetLastEventRequest")
    @ResponseWrapper(localName = "getLastEventResponse", targetNamespace = "http://soaplab.org/typedws", className = "org.soaplab.typedws.GetLastEventResponse")
    public String getLastEvent(
        @WebParam(name = "jobId", targetNamespace = "")
        JobId jobId);

    /**
     * 
     * @param jobId
     * @return
     *     returns java.util.List<org.soaplab.typedws.ResultInfo>
     */
    @WebMethod(action = "getResultsInfo")
    @WebResult(name = "resultsInfoList", targetNamespace = "")
    @RequestWrapper(localName = "getResultsInfo", targetNamespace = "http://soaplab.org/typedws", className = "org.soaplab.typedws.GetResultsInfoRequest")
    @ResponseWrapper(localName = "getResultsInfoResponse", targetNamespace = "http://soaplab.org/typedws", className = "org.soaplab.typedws.GetResultsInfoResponse")
    public List<ResultInfo> getResultsInfo(
        @WebParam(name = "jobId", targetNamespace = "")
        JobId jobId);

}
