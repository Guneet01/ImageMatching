package com.code;

//import org.apache.http.HttpHost;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.node.NodeBuilder.*;

//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.Transport.Connection;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;


public class Application {
	
	public static void main(String[] args) {
	
	
	/* method-1
	Connection connectionSettings;
	ElasticsearchClient elasticClient;
	

    connectionSettings = new Connection("http://localhost:9200/"); //local PC            
    elasticClient = new ElasticsearchClient(connectionSettings);
    
    */

//Settings settings = Settings.builder()
//                .put("cluster.name","elasticsearch").build();

Node node = nodeBuilder().clusterName("elasticsearch").node();
Client client = node.client();
	
}


}
