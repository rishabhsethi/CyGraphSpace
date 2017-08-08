package org.graphspace.javaclient;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Requests{
	
    private static JSONObject getRequest(String path, Map<String, Object> urlParams, Map<String, String> headers) throws UnirestException{
    	
		String queryPath = User.host+path;
		HttpResponse<JsonNode> getResponse = Unirest.get(queryPath)
				.basicAuth(User.username, User.password)
				.headers(headers)
				.queryString(urlParams)
				.asJson();
		JSONObject response = new JSONObject(getResponse);
		return response;
    }
    
    private static JSONObject postRequest(String path, Map<String, Object> data, Map<String, String> headers) throws UnirestException{
    	
		String queryPath = User.host+path+"/";
		JSONObject dataJson = new JSONObject(data);
		HttpResponse<JsonNode> getResponse = Unirest.post(queryPath)
				.basicAuth(User.username, User.password)
				.headers(headers)
//					.fields(data)
				.body(dataJson)
				.asJson();
		JSONObject response = new JSONObject(getResponse);
		return response;
    }
    
    private static JSONObject putRequest(String path, Map<String, Object> data, Map<String, String> headers) throws UnirestException{
		String queryPath = User.host+path;
		JSONObject dataJson = new JSONObject(data);
		HttpResponse<JsonNode> getResponse = Unirest.put(queryPath)
				.basicAuth(User.username, User.password)
				.headers(headers)
//					.fields(data)
				.body(dataJson)
				.asJson();
		JSONObject response = new JSONObject(getResponse);
		return response;
    }
    
    private static JSONObject deleteRequest(String path, Map<String, Object> urlParams, Map<String, String> headers) throws UnirestException{
    	
		String queryPath = User.host+path;
		HttpResponse<JsonNode> getResponse = Unirest.delete(queryPath)
				.basicAuth(User.username, User.password)
				.headers(headers)
				.queryString(urlParams)
				.asJson();
		JSONObject response = new JSONObject(getResponse);
		return response;
		
    }
    
    public static JSONObject makeRequest(String method, String path, Map<String, Object> urlParams, Map<String, Object> data) throws UnirestException{
    	Map<String, String> headers = new HashMap<String, String>();
    	headers.put("Accept", "application/json");
    	headers.put("Content-Type", "application/json");
    	return makeRequest(method, path, urlParams, data, headers);
    }
    
    public static JSONObject makeRequest(String method, String path, Map<String, Object> urlParams, Map<String, Object> data, Map<String, String> headers) throws UnirestException{
    	
    	if (method == "GET"){
    		return getRequest(path, urlParams, headers);
    	}
    	
    	else if (method == "POST"){
    		return postRequest(path, data, headers);
    	}
    	
    	else if (method == "PUT"){
    		return putRequest(path, data, headers);
    	}
    	
    	else{
    		return deleteRequest(path, urlParams, headers);
    	}
   
    }
}