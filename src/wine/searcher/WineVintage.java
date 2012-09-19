package wine.searcher;

import org.json.JSONException;
import org.json.JSONObject;

public class WineVintage {

	public int Vintage;

	public int PriceAverage;

	public int PriceMin;

	public int PriceMax;
	
	public WineVintage(int vintage, int priceAverage, int priceMin, int priceMax) {
		super();
		Vintage = vintage;
		PriceAverage = priceAverage;
		PriceMin = priceMin;
		PriceMax = priceMax;
	}


	public void FromJSon(JSONObject vintage) throws JSONException {
		this.Vintage= vintage.getInt("vintage");
		this.PriceAverage = vintage.getInt("price-average");
		this.PriceMin= vintage.getInt("price-min");
		this.PriceMax= vintage.getInt("price-max");

	}
}
