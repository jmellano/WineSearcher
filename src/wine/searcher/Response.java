package wine.searcher;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import wine.searcher.Enum.Currency;
import wine.searcher.response.JsonResponse;

public class Response {

	public int returnCode;
	public String listComment;
	public List<WineName> wineNameList;
	public List<WineVintage> wineVintageList;
	public Currency currency;
	
	public Response(int returnCode, List<WineName> wineNames, List<WineVintage> wineVintages, String string, Currency currency) {
		super();
		this.returnCode = returnCode;
		this.wineNameList = wineNames;
		this.wineVintageList = wineVintages;
		this.currency = currency;
	}

	public Response() {
		super();
		this.returnCode=0;
		this.currency=Currency.EURO;
		this.wineNameList= new ArrayList<WineName>();
	}

	public void convertFromJsonResponse(JSONObject jsonResponse) throws Exception {
		this.returnCode = jsonResponse.getInt("return-code");
		
		if(this.returnCode == 0 )
		{
			//JsonResponse response = JsonResponse.getJsonResponse();
			this.listComment = jsonResponse.getString("list-comment");			
			this.currency = this.getCurrency(jsonResponse.getString("list-currency-code"));
			
			if(this.listComment.equals("Wine Names List"))
			{
				JSONArray wines = jsonResponse.getJSONArray("names");
				
				for(int i =0; i<wines.length();i++)
				{
					JSONObject currentWine =wines.getJSONObject(i).getJSONObject("name");
					WineName wine = new WineName();
					wine.FromJSon(currentWine);
					this.wineNameList.add(wine); 
				}
			}
		}
		else
		{
			throw new WineNotFoundException("Pas de vin trouvé");
		}

	}

	private Currency getCurrency(String currencyString) {
		
		java.lang.String[] currencies = currencyString.split(",");
		
		if (currencies.length >0 )
		{
			String currentCurrency = currencies[0];
			
			if(currentCurrency.equals("USD"))
				return Currency.DOLLARS;
			else
				return Currency.EURO;
		}
		return Currency.EURO;
	}
	
	
	
}
