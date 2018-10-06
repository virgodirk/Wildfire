package net.virgodirk.wildfire.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.virgodirk.wildfire.util.exception.WfHttpException;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HTTP请求
 *
 * @author 李晓勇 on 2018年5月7日 下午18:18:12
 * @version Version 3.0
 */
@SuppressWarnings("all")
public class WfHttpRequest {

    /**
     * 默认网络连接超时时间（毫秒）
     */
    protected static final int CONNECT_TIMEOUT = 20000;
    
    /**
     * Socket连接超时时间（毫秒）
     */
    protected static final int SOKET_TIMEOUT = 60000;
    
    /**
     * 默认参数个数
     */
    protected static final int PARAMS_COUNT = 10;
    
    /**
     * 默认字符集名称
     */
    protected static final String DEFAULT_CHARSET = "UTF-8";
    
    
    /**
     * 请求URL
     */
    private transient String url;
    
    /**
     * Header参数
     */
    private final transient Map<String, String> headers;
    
    /**
     * 请求参数
     */
    private final transient Map<String, String> params;
    
    /**
     * Body数据
     */
    private final transient String body;
    
    /**
     * 网络连接超时时间（毫秒）
     */
    private final transient int connectTimeout;
    
    
    /**
     * {@link WfHttpRequest} 构建器
     * @return {@link WfHttpRequestBuilder}
     */
    public static WfHttpRequestBuilder builder() {
        return new WfHttpRequestBuilder();
    }
    

    /**
     * HTTP GET
     * @return {@link String} 类型请求结果
     * @throws WfHttpException {@link WfHttpException}
     */
    public String get() throws WfHttpException {
        if (!isValidUrl(url)) {
            throw new WfHttpException("网络请求URL错误");
        }
        
        final HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(getRequestConfig());
        setHttpGetHeaders(httpGet);
        setHttpGetParams(httpGet);
        
        try (
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpGet)
        ) {
            final HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException excpt) {
            throw new WfHttpException("网络请求失败，请稍候重试", excpt);
        }
    }
    
    /**
     * HTTP POST
     * <p>默认字符集：UTF-8</p>
     * @return {@link String} 类型请求结果
     * @throws WfHttpException {@link WfHttpException}
     */
    public String post() throws WfHttpException {
        return post(DEFAULT_CHARSET);
    }
    
    /**
     * HTTP POST
     * @param charset 字符集名称，如：UTF-8、GB2312等
     * @return {@link String} 类型请求结果
     * @throws WfHttpException {@link WfHttpException}
     */
    public String post(final String charset) throws WfHttpException {
        if (!isValidUrl(url)) {
            throw new WfHttpException("网络请求URL错误");
        }
        
        final HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(getRequestConfig());
        setHttpPostHeaders(httpPost);
        setHttpPostParams(httpPost, charset);
        setHttpBody(httpPost, charset);
        
        try (
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(httpPost)
        ) {
            final HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException excpt) {
            throw new WfHttpException("网络请求失败，请稍候重试", excpt);
        }
    }

    
    /**
     * 检查URL地址是否有效
     * @param url 待检查URL地址
     * @return {@code true} URL地址有效<br>
     *         {@code false} URL地址无效
     */
    private boolean isValidUrl(final String url) {
        if (url == null) {
            return false;
        }
        return url.startsWith("http://") || url.startsWith("https://");
    }
    
    /**
     * 获取请求配置
     * @return 请求配置 {@link RequestConfig}
     */
    private RequestConfig getRequestConfig() {
        return RequestConfig.custom().setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectTimeout)
                .setSocketTimeout(SOKET_TIMEOUT)
                .build();
    }
    
    
    /**
     * 给 {@link HttpGet} 添加Header参数
     * @param httpGet {@link HttpGet}
     */
    private void setHttpGetHeaders(final HttpGet httpGet) {
        if (headers == null || headers.isEmpty()) {
            return;
        }
        
        final Set<Map.Entry<String, String>> entrySet = headers.entrySet();
        for (final Map.Entry<String, String> entry : entrySet) {
            httpGet.setHeader(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * 给 {@link HttpGet} 添加请求参数
     * @param httpGet {@link HttpGet}
     */
    private void setHttpGetParams(final HttpGet httpGet) {
        if (params == null || params.isEmpty()) {
            return;
        }
        
        // 是否已经有参数
        boolean hasParam = url.indexOf('?') >= 0;
        
        // 拼接参数
        final StringBuilder paramStrBuilder = new StringBuilder();
        final Set<Map.Entry<String, String>> entrySet = params.entrySet();
        for (final Map.Entry<String, String> entry : entrySet) {
            paramStrBuilder.append(String.format("%s%s=%s", 
                    hasParam ? "&" : "?", entry.getKey(), 
                    entry.getValue() == null ? "" : entry.getValue()));
            hasParam = true;
        }
        httpGet.setURI(URI.create(url += paramStrBuilder.toString()));
    }
    
    
    /**
     * 给 {@link HttpPost} 添加Header参数
     * @param httpPost {@link HttpPost}
     */
    private void setHttpPostHeaders(final HttpPost httpPost) {
        if (headers == null || headers.isEmpty()) {
            return;
        }
        
        final Set<Map.Entry<String, String>> entrySet = headers.entrySet();
        for (final Map.Entry<String, String> entry : entrySet) {
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * 给 {@link HttpPost} 添加请求参数
     * @param httpPost {@link HttpPost}
     * @param charset 字符集名称，如：UTF-8、GB2312等
     */
    private void setHttpPostParams(final HttpPost httpPost, final String charset) {
        if (params == null || params.isEmpty()) {
            return;
        }
        
        final List<NameValuePair> paramList = new ArrayList<>();
        final Set<Map.Entry<String, String>> entrySet = params.entrySet();
        for (final Map.Entry<String, String> entry : entrySet) {
            paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(paramList, charset));
        } catch (UnsupportedEncodingException excpt) {
            excpt.printStackTrace();
        }
    }
    
    /**
     * 给 {@link HttpPost} 设置Body数据
     * @param httpPost {@link HttpPost}
     * @param charset 字符集名称，如：UTF-8、GB2312等
     */
    private void setHttpBody(final HttpPost httpPost, final String charset) {
        if (body == null || body.trim().length() <= 0) {
            return;
        }
        httpPost.setEntity(new StringEntity(body, charset));
    }
    
    
    /**
     * 构造 {@link WfHttpRequest}
     * @param builder {@link WfHttpRequestBuilder}
     */
    protected WfHttpRequest(final WfHttpRequestBuilder builder) {
        this.url = builder.url;
        this.headers = builder.headers;
        this.params = builder.params;
        this.body = builder.body;
        this.connectTimeout = builder.connectTimeout;
    }


    /**
     * {@link WfHttpRequest} 构建器
     *
     * @author 李晓勇 on 2018年5月7日 下午18:18:12
     * @version Version 2.0
     */
    public static class WfHttpRequestBuilder {
        
        /**
         * 请求URL
         */
        private transient String url;
        
        /**
         * Header参数
         */
        private final transient Map<String, String> headers;
        
        /**
         * 请求参数
         */
        private final transient Map<String, String> params;
        
        /**
         * Body数据
         */
        private transient String body;
        
        /**
         * 请求超时时间（毫秒）
         */
        private transient int connectTimeout;


        /**
         * 构造 {@link WfHttpRequestBuilder}
         */
        public WfHttpRequestBuilder() {
            url = "";
            headers = new HashMap<>(PARAMS_COUNT);
            params = new HashMap<>(PARAMS_COUNT);
            body = "";
            connectTimeout = CONNECT_TIMEOUT;
        }

        
        /**
         * 设置请求URL
         * @param url 请求URL
         * @return {@link WfHttpRequestBuilder}
         */
        public WfHttpRequestBuilder setUrl(final String url) {
            this.url = url;
            return this;
        }
        
        /**
         * 设置请求超时时间（毫秒）
         * <p>默认值：20000毫秒</p>
         * @param timeout 请求超时时间（毫秒）
         * @return {@link WfHttpRequestBuilder}
         */
        public WfHttpRequestBuilder setConnectTimeout(final int timeout) {
            this.connectTimeout = timeout;
            return this;
        }

        /**
         * 设置Body数据
         * @param data Body数据
         * @return {@link WfHttpRequestBuilder}
         */
        public WfHttpRequestBuilder setBody(final String data) {
            this.body = data;
            return this;
        }
        
        
        /**
         * 添加Header参数
         * @param key 参数名称
         * @param value 参数值
         * @return {@link WfHttpRequestBuilder}
         */
        public WfHttpRequestBuilder addHeader(final String key, final String value) {
            headers.put(key, value);
            return this;
        }
        
        /**
         * 添加Header参数
         * @param headers 参数列表
         * @return {@link WfHttpRequestBuilder}
         */
        public WfHttpRequestBuilder addHeaders(final Map<String, String> headers) {
            this.headers.putAll(headers);
            return this;
        }

        
        /**
         * 添加请求参数
         * @param key 参数名称
         * @param value 参数值
         * @return {@link WfHttpRequestBuilder}
         */
        public WfHttpRequestBuilder addParam(final String key, final String value) {
            params.put(key, value);
            return this;
        }
        
        /**
         * 添加请求参数
         * @param params 参数列表
         * @return {@link WfHttpRequestBuilder}
         */
        public WfHttpRequestBuilder addParams(final Map<String, String> params) {
            this.params.putAll(params);
            return this;
        }
        
        
        /**
         * 清空Header参数
         * @return {@link WfHttpRequestBuilder}
         */
        public WfHttpRequestBuilder clearHeaders() {
            if (headers == null || headers.isEmpty()) {
                return this;
            }
            headers.clear();
            return this;
        }
        
        /**
         * 清空请求参数
         * @return {@link WfHttpRequestBuilder}
         */
        public WfHttpRequestBuilder clearParams() {
            if (params == null || params.isEmpty()) {
                return this;
            }
            params.clear();
            return this;
        }
        
        
        /**
         * 构建 {@link WfHttpRequest}
         * @return {@link WfHttpRequest}
         */
        public WfHttpRequest build() {
            return new WfHttpRequest(this);
        }

    }
}