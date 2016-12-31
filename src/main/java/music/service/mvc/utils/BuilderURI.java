package music.service.mvc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuilderURI {

	private static final Logger log = LoggerFactory.getLogger(BuilderURI.class);

	public static URI buildURI(String method, Map<String, String> params) {
		AccessTokenHolder holder = AccessTokenHolder.getInstance();
		URIBuilder builder = new URIBuilder();
		builder.setScheme(holder.getTokenFromFileSystem(SystemProperties.SCHEME))
				.setHost(holder.getTokenFromFileSystem(SystemProperties.API_HOST)).setPath("/method/" + method)
				.setParameter(SystemProperties.USER_ID, holder.getTokenFromFileSystem(SystemProperties.USER_ID))
				.setParameter(SystemProperties.ACCESS_TOKEN,
						holder.getTokenFromFileSystem(SystemProperties.ACCESS_TOKEN))
				.setParameter(SystemProperties.VERSION, holder.getTokenFromFileSystem(SystemProperties.VERSION));
		Params.addParamsToURI(builder, params);
		try {
			return builder.build();
		} catch (URISyntaxException e) {
			log.debug("Cannot build correct uri: " + e.getStackTrace());
		}

		return null;
	}

	@SuppressWarnings("deprecation")
	public static DefaultHttpClient getThreadSafeClient() {
		DefaultHttpClient client = new DefaultHttpClient();
		ClientConnectionManager mgr = client.getConnectionManager();
		HttpParams params = client.getParams();
		client = new DefaultHttpClient(new ThreadSafeClientConnManager(params, mgr.getSchemeRegistry()), params);
		return client;
	}

	public static class AccessTokenHolder {

		private static final String CONFIG_LOCATION = "C:/Users/Public/token-config.properties";

		private static AccessTokenHolder INSTANCE = new AccessTokenHolder();

		public static AccessTokenHolder getInstance() {
			return INSTANCE;
		}

		public String getTokenFromFileSystem(String property) {
			Properties props = new Properties();
			FileInputStream config;
			try {
				config = new FileInputStream(CONFIG_LOCATION);
				props.load(config);
				return props.getProperty(property);
			} catch (IOException e) {
				log.debug("Problem with load token-config.properties. Check it.");
			}
			return null;
		}

		// TODO доделать когда-нибудь
		public String getTokenByQuery() {
			URIBuilder builder = new URIBuilder();
			builder.setScheme(getTokenFromFileSystem(SystemProperties.SCHEME))
					.setHost(getTokenFromFileSystem(SystemProperties.OAUTH_HOST)).setPath("/authorize")
					.setParameter(SystemProperties.CLIENT_ID, getTokenFromFileSystem(SystemProperties.CLIENT_ID))
					.setParameter(SystemProperties.SCOPE, getTokenFromFileSystem(SystemProperties.SCOPE))
					.setParameter(SystemProperties.REDIRECT_URI, getTokenFromFileSystem(SystemProperties.REDIRECT_URI))
					.setParameter(SystemProperties.RESPONSE_TYPE,
							getTokenFromFileSystem(SystemProperties.RESPONSE_TYPE))
					.setParameter(SystemProperties.VERSION, getTokenFromFileSystem(SystemProperties.VERSION));
			URI uri;
			HttpResponse response;

			try {
				uri = builder.build();
				if (uri == null)
					throw new RuntimeException("URI can't be empty!");
				HttpGet post = new HttpGet(uri);
				response = BuilderURI.getThreadSafeClient().execute(post);
				post.abort();
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					InputStream instream = null;
					String json;
					try {
						instream = entity.getContent();
						try (@SuppressWarnings("resource")
						Scanner s = new Scanner(instream).useDelimiter("\\A")) {
							json = s.hasNext() ? s.next() : "";
						}
					} finally {
						if (instream != null)
							instream.close();
					}
				}
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";
		}
	}
}
