package com.jackpot.base.utils;

import com.jackpot.base.base.BaseUtil;
import com.jackpot.base.base.ContentType;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class HttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).setConnectionRequestTimeout(60000).build();

    public static String sendGet(String url) {
        return sendGet(url, null, null);
    }

    public static String sendGetWithParams(String url, Map<String, String> params) {
        return sendGet(url, params, null);
    }

    public static String sendGetWithHeaders(String url, Map<String, String> headers) {
        return sendGet(url, null, headers);
    }

    public static String sendGet(String url, Map<String, String> params, Map<String, String> headers) {
        String responseContent = null;
        try {

            if (params != null){
                StringBuffer sbf = new StringBuffer(url).append("?");
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    sbf.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                }
                sbf.setLength(sbf.length() - 1);
                url = sbf.toString();
            }
            HttpGet httpGet = new HttpGet(url);
            if (headers != null){
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }
            CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseContent = EntityUtils.toString(httpEntity, BaseUtil.UTF8);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return responseContent;
    }


    public static String sendPost(String url, Map<String, String> params) {
        try {
            List<NameValuePair> nvps = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            HttpEntity entity = new UrlEncodedFormEntity(nvps, BaseUtil.UTF8);
            return sendPost(url, entity);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public static String sendPost(String url, String params) {
        HttpEntity entity = new StringEntity(params, BaseUtil.UTF8);
        return sendPost(url, entity);
    }

    public static String sendPost(String url, String params, ContentType contentType, Map<String, String>... headers) {
        HttpEntity entity = new StringEntity(params, BaseUtil.UTF8);

        int length = headers.length == 0 ? 1 : headers[0].size() + 1;
        Header[] headerArray = new Header[length];
        headerArray[0] = new BasicHeader(HttpHeaders.CONTENT_TYPE, contentType.getType());
        if (headers.length > 0) {
            int i = 0;
            for (Map.Entry<String, String> entry : headers[0].entrySet()) {
                headerArray[i + 1] = new BasicHeader(entry.getKey(), entry.getValue());
                i ++;
            }
        }
        return sendPost(url, entity, headerArray);
    }

    public static String sendJsonPost(String url, String params) {
        HttpEntity entity = new StringEntity(params, BaseUtil.UTF8);
        Header header = new BasicHeader("Content-Type", "application/json");
        return sendPost(url, entity, header);
    }

    private static String sendPost(String url, HttpEntity entity, Header... headers) {
        String responseContent = null;
        try {
            HttpPost httpPost = new HttpPost(url);// 创建httpPost
            if (entity != null) httpPost.setEntity(entity);
            if (headers != null && headers.length > 0) httpPost.setHeaders(headers);
            CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return responseContent;
    }

    public static String sendPutWithHeaders(String url, Map<String, String> headers) {
        String responseContent = null;
        try {
            HttpPut httpPut = new HttpPut(url);
            if (headers != null){
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpPut.setHeader(entry.getKey(), entry.getValue());
                }
            }
            CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
            CloseableHttpResponse httpResponse = httpClient.execute(httpPut);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return responseContent;
    }

    public static String sendHttpsPost(String url, Map<String, String> params) {
        String responseContent = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<>();
            for (String key : params.keySet()) {
                nvps.add(new BasicNameValuePair(key, params.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return responseContent;
    }

    public static String sendJsonHttpsPost(String url, String params) {
        String responseContent = null;
        try {
            HttpPost httpPost = new HttpPost(url);// 创建httpPost
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(params, "UTF-8"));
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseContent = EntityUtils.toString(httpEntity, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseContent;
    }

    public static String sendHttpsPost(String url, String params, String charset) {
        String responseContent = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(params, charset));
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseContent = EntityUtils.toString(httpEntity, charset);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return responseContent;
    }

    public static String sendHttpsPost(String url, String params) {
        return sendHttpsPost(url, params, "UTF-8");
    }

    public static byte[] sendHttpsPost(String url, InputStream params) {
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new InputStreamEntity(params));
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                return EntityUtils.toByteArray(httpEntity);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return sslsf;
    }
}