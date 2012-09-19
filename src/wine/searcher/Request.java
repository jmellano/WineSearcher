package wine.searcher;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Request {

	public String WineName;
	
	public String WineYear;
	
	public String Location;
	
	public Response Response;
	
	public JSONObject JsonResponse;
	
	public String WideSearch;
	
	public String Format;
	
	public String KeywordMode;
	
	public String AutoExpand;
	
	public String CurrencyCode;
	
	public Request() {
		// TODO Auto-generated constructor stub
		this.Format = "J";
		this.WideSearch = "V";
		this.KeywordMode= "X";
		this.AutoExpand= "Y";
		this.CurrencyCode= "euro";		
	}
	
	
	public void getWinesInformations() throws Exception
	{
		try{
			getWineInformations();
		}
		catch (WineNotFoundException e)
		{
			this.WideSearch ="Y";
			getWineInformations();
		}
	}


	private void getWineInformations() throws Exception {
		String url = getFormatedRequest();
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null)
		     {
				String responseString = EntityUtils.toString(entity);
				JSONObject JsonResponse = new JSONObject(responseString).getJSONObject("wine-searcher");
				
				this.Response = new Response();
				this.Response.convertFromJsonResponse(JsonResponse);
		     }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private String getFormatedRequest() throws Exception {
		final String preUrl ="http://api.wine-searcher.com/wine-select-api.lml?Xkey=jlnmll821583&Xversion=4";
		final String postUrl="&Xautoexpand="+this.AutoExpand + 
				"&Xcurrencycode="+this.CurrencyCode + 
				"&Xkeyword_mode="+this.KeywordMode + 
				"&Xwidesearch="+this.WideSearch +
				"&Xformat="+this.Format;
		
		if(this.WineName.equals(""))
		{
			throw new Exception("Veuillez renseigner un nom de vin a chercher");
		}
		else
		{
			this.WineName=this.WineName.replace(" ","+");
		}
		if(this.WineYear.equals(""))
		{
			throw new Exception("Veuillez renseigner une année");
		}
		if(this.Location.equals(""))
		{
			this.Location="France";
		}
			
			
		String url = preUrl + "&Xwinename="+this.WineName+"&Xvintage="+this.WineYear+"&Xlocation="+this.Location+postUrl;
		return url;
	}

}
