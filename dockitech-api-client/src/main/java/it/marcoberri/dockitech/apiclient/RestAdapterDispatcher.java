package it.marcoberri.dockitech.apiclient;

import java.text.DateFormat;
import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

public class RestAdapterDispatcher {

    public static final int DEFAULT_TIMEOUT = 300000;

    static Integer timeout = DEFAULT_TIMEOUT;

    public static RestAdapter getClientRestAdapter(Integer timeout, String uri) {
	if (timeout == null || timeout == 0)
	    RestAdapterDispatcher.timeout = DEFAULT_TIMEOUT;
	else
	    RestAdapterDispatcher.timeout = timeout;

	return buildClientRestAdapter(uri);
    }

    private static RestAdapter buildClientRestAdapter(String uri) {

	final OkHttpClient okHttpClient = new OkHttpClient();
	okHttpClient.setReadTimeout(RestAdapterDispatcher.timeout, TimeUnit.MILLISECONDS);
	okHttpClient.setConnectTimeout(RestAdapterDispatcher.timeout, TimeUnit.MILLISECONDS);
	final OkClient httpClient = new OkClient(okHttpClient);

	final GsonBuilder builder = new GsonBuilder();
	builder.setDateFormat(DateFormat.MILLISECOND_FIELD);

	/*builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
	    @Override
	    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		return new Date(json.getAsJsonPrimitive().getAsLong());
	    }

	});

	builder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
	    @Override
	    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
		return src == null ? null : new JsonPrimitive(src.getTime());
	    }
	});
	*/

	final Gson gson = builder.create();

	return new RestAdapter.Builder().setEndpoint(uri).setLogLevel(RestAdapter.LogLevel.FULL).setConverter(new GsonConverter(gson)).setClient(httpClient).build();
    }
}
