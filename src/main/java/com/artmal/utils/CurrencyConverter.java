package com.artmal.utils;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * Used to convert between different currencies.
 * @author Artem Malchenko
 */
public class CurrencyConverter {
    /**
     *
     * @param from Currency to convert from(eg. USD).
     * @param to Currency to convert to(eg. UAH).
     */
    public static float getConversionRate(String from, String to) throws IOException {
        HttpClientBuilder builder = HttpClientBuilder.create();
        try (CloseableHttpClient httpclient = builder.build()) {
            HttpGet httpGet = new HttpGet("http://quote.yahoo.com/d/quotes.csv?s=" + from + to + "=X&f=l1&e=.csv");
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httpGet, responseHandler);

            return Float.parseFloat(responseBody);
        }
    }
}
