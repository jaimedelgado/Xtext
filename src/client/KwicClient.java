package client;

import javax.xml.ws.Holder;

import org.soaplab.text_mining.kwic.Kwic;
import org.soaplab.text_mining.kwic.KwicService;
import org.soaplab.typedws.JobId;

public class KwicClient {
	public KwicClient(){}
	public static String runOutput(
			String query,
			String input_direct_data,
			String input_url,
			Long windowSize,
			Long limit,
			String shortBy,
			boolean xml){
		KwicService kwicService = new KwicService();
		Kwic kwic = kwicService.getKwicPort();
		Holder<String>  report = new Holder<String>(), 
				output = new Holder<String>(), 
				outputUrl = new Holder<String>();
		Holder<Long> detailedStatus = new Holder<Long>();		
		String jobId = kwic.run(
				query, //String query
				input_direct_data, //String Input_direct_data
				input_url, //String input_url
				windowSize, // Long WindowSize
				limit, // Long limit
				shortBy, // String short by
				xml); //boolean xml
		JobId job = new JobId();
		job.setJobId(jobId);
		kwic.waitfor(job);
		kwic.getResults(job, report, detailedStatus, output, outputUrl);
		return output.value;
	}
	public static void main(String[] args){
		KwicService kwicService = new KwicService();
		Kwic kwic = kwicService.getKwicPort();
		Holder<String>  report = new Holder<String>(), 
						output = new Holder<String>(), 
						outputUrl = new Holder<String>();
		Holder<Long> detailedStatus = new Holder<Long>();
		System.out.println(kwicService.getWSDLDocumentLocation());
		
		String query = "perro", 
				input_direct_data = "Juan tiene un perro muy feo. Nerea tiene otro perro mñas bonito"; 
		String jobId = kwic.run(query, //String query
				input_direct_data, //String Input_direct_data
				null, //String input_url
				null, // Long WindowSize
				null, // Long limit
				null, // String short by
				false); //boolean xml
		
		System.out.println(jobId);
		JobId job = new JobId();
		job.setJobId(jobId);
		kwic.waitfor(job);
		kwic.getResults(job, report, detailedStatus, output, outputUrl);
		System.out.println("Report: " +report.value);
		System.out.println("DetailedStatus: " + detailedStatus.value);
		System.out.println("Output: " + output.value);
		System.out.println("OutputUrl: " + outputUrl.value);
		        	
           
	}

}
