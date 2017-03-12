package nl.hu.iac.wsproducer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.jws.WebService;

import nl.hu.iac.wsinterface.PostcodeFault;
import nl.hu.iac.wsinterface.ObjectFactory;
import nl.hu.iac.wsinterface.Fault;
import nl.hu.iac.wsinterface.PostcodeRequestType;
import nl.hu.iac.wsinterface.PostcodeResponseType;
import nl.hu.iac.wsinterface.PostcodeServiceInterface;

@WebService(endpointInterface = "nl.hu.iac.wsinterface.PostcodeServiceInterface")
public class PostcodeServiceImpl implements PostcodeServiceInterface {

	@Override
	public PostcodeResponseType giveAdress(PostcodeRequestType request) throws Fault {
		ObjectFactory factory = new ObjectFactory();
		PostcodeResponseType response = factory.createPostcodeResponseType();
		try {
			String result = "Vianen";
			// x en power zijn altijd gehele getallen dan is er geen afronding
			response.setPlaats(result);
			response.setStraat("Straat1");

//			String stringUrl = "https://postcode-api.apiwise.nl/v2/postcodes/";
//			URL url = new URL(stringUrl);
//			URLConnection uc = url.openConnection();
//
//			uc.setRequestProperty("X-Api-Key", "SDvNheunxg1gV9yBKwEn88NgPs3Lgmy18T7sWCbn");
//
//			InputStreamReader inputStreamReader = new InputStreamReader(uc.getInputStream());
		} catch (RuntimeException e) {
			PostcodeFault x = factory.createPostcodeFault();
			x.setErrorCode((short) 1);
			x.setMessage("Kan de plaats van " + request.getPostcode()
					+ " niet vinden.");
			Fault fault = new Fault(
					"Er ging iets mis met het uitzoeken van de plaats", x);
			throw fault;
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
		}
		return response;
	}

}
