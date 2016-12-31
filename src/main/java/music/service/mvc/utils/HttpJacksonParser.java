package music.service.mvc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import music.service.orm.entites.CommonInformation;
import music.service.orm.entites.Music;
import music.service.orm.entites.User;

public class HttpJacksonParser {

	public static List<Music> getList(URI uri, User user) {
		if (uri == null) throw new RuntimeException("URI is empty");
		try {
			HttpResponse response = BuilderURI.getThreadSafeClient().execute(new HttpGet(uri));
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = null;
				String json;
			    try {
			    	instream = entity.getContent();
			    	try(@SuppressWarnings("resource")
					Scanner s = new Scanner(instream).useDelimiter("\\A")) {            
			    	    json = s.hasNext() ? s.next() : "";
			    	}
			    } finally {
			    	if (instream != null)
			    		instream.close();
			    }
			    return JsonConverter.INSTANCE.fromJsonToObject(json, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Music>();

	}
	public static class JsonConverter {
		
		public static final JsonConverter INSTANCE = new JsonConverter();
		
		private List<Music> fromJsonToObject(String json, User user) {
			List<Music> musicList = new ArrayList<Music>();
			try {
				List<String> nodesId = new ObjectMapper().readTree(json).findValuesAsText("id");
				List<String> nodesOwnerId = new ObjectMapper().readTree(json).findValuesAsText("owner_id");
				List<String> nodesTitle = new ObjectMapper().readTree(json).findValuesAsText("title");
				List<String> nodesURL = new ObjectMapper().readTree(json).findValuesAsText("url");
				// TODO: Доделать загрузку полей, привести класс в порядок
				for (String id : nodesId) {
					Music music = new Music(id, nodesOwnerId.get(nodesId.indexOf(id)), 
							nodesTitle.get(nodesId.indexOf(id)), nodesURL.get(nodesId.indexOf(id)), null, user);
			        //music.setCommonInformation(new CommonInformation("1", "1", new Long(1), music));
					musicList.add(music);
				}
				return musicList;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return musicList;
		}
	}
}
