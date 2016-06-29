package com.pfz;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.client.transport.TransportClient;

public class ElasticSearchHandler {
	
	   private static Client client;

	  
	    
	    
	  
	    private static void init() throws Exception{
	        
	    	
	        client = TransportClient.builder().build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
	        
	    }
	    
//	    private static String jsonUtil() throws IOException{
//	    	XContentBuilder builder = XContentFactory.jsonBuilder().startObject().
//	    			field("user","pfzhang").
//	    			endObject();
//	    	
//	    	return builder.toString();
//
//	    }
	    
	    private static void close(){
	    	client.close();
	    }
	    
	    
	    
	    private void createIndex(){
	    	
	    		
	    		String json = "{" +
	    		        "\"user\":\"pfzhang\"," +
	    		        "\"postDate\":\"2016-04-17\"," +
	    		        "\"message\":\"trying out Elasticsearch\"" +
	    		    "}";
	    		
				IndexResponse response = client.prepareIndex("twitter", "tweet")
				        .setSource(json)
				        .get();
				
				// Index name
				String _index = response.getIndex();
				// Type name
				String _type = response.getType();
				// Document ID (generated or not)
				String _id = response.getId();
				// Version (if it's the first time you index this document, you will get: 1)
				long _version = response.getVersion();
				// isCreated() is true if the document is a new one, false if it has been updated
				boolean created = response.isCreated();
				System.out.println("_index="+ _index);
				System.out.println("_type="+ _type);
				System.out.println("_id="+ _id);
				System.out.println("_version="+ _version);
				System.out.println("created="+ created);
			
	    }
	    	
	    private void getIndex(){
	    	
	    	GetResponse response = client.prepareGet("twitter", "tweet", "AVQjrRWYGJg3utqzysRq")
	    	        .setOperationThreaded(false)
	    	        .get();
	    	System.out.println("response="+ response.getSourceAsString());
	    	
	    }
	    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			init();
			ElasticSearchHandler elasticSearchHandler = new ElasticSearchHandler();
//			elasticSearchHandler.createIndex();	
			elasticSearchHandler.getIndex();
			close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
    
	
	
}

	