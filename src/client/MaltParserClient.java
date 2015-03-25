package client;

import javax.xml.ws.Holder;

import org.soaplab.syntactic_tagging.malt_parser.MaltParser;
import org.soaplab.syntactic_tagging.malt_parser.MaltParserService;
import org.soaplab.typedws.JobId;

public class MaltParserClient {
	public MaltParserClient(){}
	public static String runOutput(String input_direct_data,
							String input_url,
							String language){
		MaltParserService maltParserService = new MaltParserService();
		MaltParser maltParser = maltParserService.getMaltParserPort();
		Holder<String>  report = new Holder<String>(), 
						output = new Holder<String>(), 
						outputUrl = new Holder<String>();
		Holder<Long> detailedStatus = new Holder<Long>();		
		String jobId = maltParser.run(
				input_direct_data, //String Input_direct_data
				input_url, //String input_url
				language); //String language
		JobId job = new JobId();
		job.setJobId(jobId);
		maltParser.waitfor(job);
		maltParser.getResults(job, report, detailedStatus, output, outputUrl);
		return output.value;
	}
	public static void main(String[] args){
		MaltParserService maltParserService = new MaltParserService();
		MaltParser maltParser = maltParserService.getMaltParserPort();
		Holder<String>  report = new Holder<String>(), 
						output = new Holder<String>(), 
						outputUrl = new Holder<String>();
		Holder<Long> detailedStatus = new Holder<Long>();
		System.out.println(maltParserService.getWSDLDocumentLocation());
		
		String input_direct_data = "Juan tiene un perro muy feo. Nerea tiene otro perro mñas bonito"; 
		String jobId = maltParser.run(
				input_direct_data, //String Input_direct_data
				null, //String input_url
				"es"); //String language
		
		System.out.println(jobId);
		JobId job = new JobId();
		job.setJobId(jobId);
		maltParser.waitfor(job);
		maltParser.getResults(job, report, detailedStatus, output, outputUrl);
		System.out.println("Report: " +report.value);
		System.out.println("DetailedStatus: " + detailedStatus.value);
		System.out.println("Output: " + output.value);
		System.out.println("OutputUrl: " + outputUrl.value);
		        	
           
	}

}
