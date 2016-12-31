package music.service.mvc.utils;

import java.util.Map;

import org.apache.http.client.utils.URIBuilder;

public class Params {
	
	public static final String SEARCH_Q = "q";
	
	public static final String GET_COUNT = "count";
	
	public static final String GET_NEED_USER = "need_user";
	
	public static final String  GET_OFFSET = "offset";
	
	public static final String  GET_AUDIOS = "audios";
	
	public static final String  USER_ID = "userId";
	
	public static URIBuilder addParamsToURI(URIBuilder builder, Map<String, String> params) {
		if (params.containsKey(Params.SEARCH_Q))
	    	builder.setParameter(Params.SEARCH_Q, params.get(Params.SEARCH_Q));
		if (params.containsKey(GET_COUNT))
	    	builder.setParameter(GET_COUNT, params.get(GET_COUNT));
		if (params.containsKey(GET_OFFSET))
	    	builder.setParameter(GET_OFFSET, params.get(GET_OFFSET));
		if (params.containsKey(GET_AUDIOS))
				builder.setParameter(GET_AUDIOS, params.get(GET_AUDIOS));
		return builder;
	}
}
