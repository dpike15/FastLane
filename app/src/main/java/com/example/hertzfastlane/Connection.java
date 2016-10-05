package com.example.hertzfastlane;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dapik on 10/5/2016.
 */

public class Connection {
    public static CloudantClient getClient() throws MalformedURLException{
       CloudantClient client = ClientBuilder.url(new URL("https://6a4c10ac-077f-4d8c-9ca3-53f0e84f3d5a-bluemix:e93cc83b1d85bd5c712886ba101bd9531ca36d464bc40d60f982ec46b3db8f5f@6a4c10ac-077f-4d8c-9ca3-53f0e84f3d5a-bluemix.cloudant.com"))
                .username("6a4c10ac-077f-4d8c-9ca3-53f0e84f3d5a-bluemix")
                .password("e93cc83b1d85bd5c712886ba101bd9531ca36d464bc40d60f982ec46b3db8f5f")
                .build();

        return client;
    }
}


