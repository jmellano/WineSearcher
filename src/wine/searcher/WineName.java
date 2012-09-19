package wine.searcher;

import org.json.JSONException;
import org.json.JSONObject;

public class WineName {

	public String Name;

	public String Region;

	public String Grape;

	public int PriceAverage;

	public int PriceMin;

	public int PriceMax;

	public void FromJSon(JSONObject wine) throws JSONException {
		this.Name = wine.getString("wine-name");
		this.Region= wine.getString("region");
		this.Grape = wine.getString("grape");
		this.PriceAverage = wine.getInt("price-average");
		this.PriceMin= wine.getInt("price-min");
		this.PriceMax= wine.getInt("price-max");

	}

}
