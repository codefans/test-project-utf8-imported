package com.codefans.practicetask.httprequest;

import com.codefans.reusablecode.util.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @Author: ShengzhiCai
 * @Date: 2017-11-11 9:49
 */

public class HttpRequest {

    private String url;
    private String method;
    private Map<String, String> headers = new HashMap<String, String>();
    private Map<String, String> params = new HashMap<String, String>();

    public HttpRequest(String url, String method, Map<String, String> headers, Map<String, String> params) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.params = params;
    }

    public HttpResponse call() {
        HttpResponse httpResponse = null;
        if(StringUtils.isNotEmpty(method)) {
            if(method.equals(HttpParams.GET)) {
                httpResponse = this.doGet();
            } else if(method.equals(HttpParams.POST)) {
                httpResponse = this.doPost();
            }
        }
        return httpResponse;

    }

    public HttpResponse doGet() {

        HttpResponse httpResponse = new HttpResponse();
        try {

            url = this.setGetUrl(url);
            CloseableHttpClient httpclient = null;

            if(url.startsWith("https")) {
                httpclient = this.createSSLInsecureClient();
            } else {
                httpclient = HttpClients.createDefault();
            }

            HttpGet httpGet = new HttpGet(url);
            this.setHeaders(httpGet);
            CloseableHttpResponse response = httpclient.execute(httpGet);

            try {


                // do something useful with the response body
                StatusLine statusLine = response.getStatusLine();
                HttpEntity entity = response.getEntity();

                this.setResponse(httpResponse, response);

                httpResponse.setStatusLine(statusLine);
                httpResponse.setEntity(entity);

                System.out.println(statusLine);

                if(statusLine != null) {
                    httpResponse.setResponseCode(statusLine.getStatusCode());
                    httpResponse.setResponseMsg(statusLine.getReasonPhrase());
                }
                if(entity != null) {
                    httpResponse.setContentStr(IOUtils.getStr(entity.getContent()));
                }

                // and ensure it is fully consumed
                EntityUtils.consume(entity);

            } finally {
                response.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return httpResponse;
    }



    public HttpResponse doPost() {
        HttpResponse httpResponse = new HttpResponse();
        try {

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpResponse response = httpclient.execute(httpPost);

            try {
                System.out.println(response.getStatusLine());
                HttpEntity entity = response.getEntity();
                // do something useful with the response body
                Locale local = response.getLocale();
                ProtocolVersion protocolVersion = response.getProtocolVersion();
                InputStream content = entity.getContent();

                // and ensure it is fully consumed
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setResponse(HttpResponse httpResponse, CloseableHttpResponse response) throws IOException {

        Locale local = response.getLocale();
        ProtocolVersion protocolVersion = response.getProtocolVersion();
        Header[] responseHeaders = response.getAllHeaders();

        httpResponse.setLocal(local);
        httpResponse.setProtocolVersion(protocolVersion);
        httpResponse.setResponseHeaders(responseHeaders);

    }

    public String setGetUrl(String url) {
        StringBuffer sb = new StringBuffer();

        if(params != null) {
            Iterator<String> iter = params.keySet().iterator();
            String name = "";
            String value = "";
            int count = 0;
            while(iter.hasNext()) {
                name = iter.next();
                value = params.get(name);
                if(count == 0) {
                    sb.append(url).append("?");
                } else {
                    sb.append("&");
                }
                sb.append(name).append("=").append(value);
                count++;
            }
        }

        return sb.toString();

    }

    public void setHeaders(HttpGet httpGet) {
        if(headers != null) {
            Iterator<String> iter = headers.keySet().iterator();
            String name = "";
            String value = "";
            while(iter.hasNext()) {
                name = iter.next();
                value = headers.get(name);
                httpGet.setHeader(name, value);
            }
        }

    }

    /**
     * 创建 SSL连接
     * @return
     * @throws GeneralSecurityException
     */
    private CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl)
                        throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert)
                        throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns,
                                   String[] subjectAlts) throws SSLException {
                }

            });

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();

        } catch (GeneralSecurityException e) {
            throw e;
        }
    }


}
