package com.code;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.*;
import org.elasticsearch.common.transport.TransportAddress;
public class App2 {

	public static void main(String args[])
    {
            //System.out.println("Hello World");
            Client client = null;
            // = new TransportClient();
            Settings settings = Settings.builder()
                    .put("cluster.name","elasticsearch").build();
            try{
            	
             client =TransportClient.builder().build()
            	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host1"), 9300))
            	        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9300));
            catch(Exception e)
            {
                    e.printStackTrace();
            }
}
