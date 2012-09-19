package wine.searcher.response;

public abstract class JsonResponse {
	public static String listComment;

	public static JsonResponse getJsonResponse() {
		if(listComment.equals("Wine Names List"))
		{
			return new WineNameListJsonResponse();
		}
		return null;
	}
}
