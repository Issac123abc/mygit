package cn.com.nexwise.guess.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HttpClientUtils {

	/**
	 * Log4j日志记录器
	 */
	private final static Logger LOG = LoggerFactory.getLogger(HttpClientUtils.class);

	/**
	 * default 30 seconds
	 */
	private int socketTimeout = 30000;

	/**
	 * default 30 seconds
	 */
	private int connectTimeout = 30000;

	/**
	 * default 10 seconds
	 */
	private String url = "http://localhost:8081";

	/**
	 * 请求配置
	 */
	private RequestConfig requestConfig;

	/**
	 * post调用(json)
	 * 
	 * @param url
	 * @param params
	 *            将参数转换成json
	 * @return
	 */
	public String postJson(String url, Map<String, Object> params) {
		CloseableHttpClient client = null;
		HttpPost httppost = null;
		try {
			RequestConfig requestConfig = createRequestConfig();
			client = HttpClients.custom().build();
			httppost = new HttpPost(url);
			httppost.setConfig(requestConfig);
			StringEntity stringEntity = null;
			if (params == null || params.isEmpty()) {
				stringEntity = new StringEntity(null, ContentType.APPLICATION_JSON);
			} else {
				stringEntity = new StringEntity(JSON.toJSONString(params), ContentType.APPLICATION_JSON);
			}
			httppost.setEntity(stringEntity);
			HttpResponse response = client.execute(httppost);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				return EntityUtils.toString(response.getEntity());
			} else {
				LOG.error("Http Client JSON POST fail ,url=" + url + ", HttpStatus="
						+ response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e) {
			LOG.error("Http Client JSON POST error,url=" + url + ", cause : " + e.getMessage());
		} finally {
			try {
				if (httppost != null) {
					httppost.releaseConnection();
				}
				if (client != null) {
					client.close();
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * HTTP POST 请求调用
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return 返回字符串结果或NULL
	 */
	public String post(String url, Map<String, Object> params) {
		CloseableHttpClient client = null;
		HttpPost httppost = null;
		try {
			RequestConfig requestConfig = createRequestConfig();

			client = HttpClients.custom().build();

			httppost = new HttpPost(url);
			httppost.setConfig(requestConfig);

			List<NameValuePair> nvps = initParams(params);
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
			httppost.setEntity(urlEncodedFormEntity);

			HttpResponse response = client.execute(httppost);

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				return EntityUtils.toString(response.getEntity());
			} else {
				LOG.error("请求失败,url=" + url + ", HttpStatus=" + response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e) {
			LOG.error("请求异常,url=" + url + ", 异常原因 : " + e.getMessage());
		} finally {
			try {
				if (httppost != null) {
					httppost.releaseConnection();
				}
				if (client != null) {
					client.close();
				}
			} catch (Exception e) {
			}
		}

		return null;
	}
	/**
	 * post请求 自定义Header
	 * @param url
	 * @param params
	 * @param header
	 * @return
	 */
	public String post(String url, Map<String, Object> params,Map<String,String> header) {
		CloseableHttpClient client = null;
		HttpPost httppost = null;
		try {
			RequestConfig requestConfig = createRequestConfig();

			client = HttpClients.custom().build();

			httppost = new HttpPost(url);
			httppost.setConfig(requestConfig);
			if(header!=null){
				for (Entry<String, String> entry : header.entrySet()) {
					httppost.setHeader(entry.getKey(), entry.getValue());
				}
			}
			List<NameValuePair> nvps = initParams(params);
			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
			httppost.setEntity(urlEncodedFormEntity);

			HttpResponse response = client.execute(httppost);

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				return EntityUtils.toString(response.getEntity());
			} else {
				LOG.error("请求失败,url=" + url + ", HttpStatus=" + response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e) {
			LOG.error("请求异常,url=" + url + ", 异常原因 : " + e.getMessage());
		} finally {
			try {
				if (httppost != null) {
					httppost.releaseConnection();
				}
				if (client != null) {
					client.close();
				}
			} catch (Exception e) {
			}
		}

		return null;
	}public String post(String url,String body,Map<String,String> header) {
		CloseableHttpClient client = null;
		HttpPost httppost = null;
		try {
			RequestConfig requestConfig = createRequestConfig();

			client = HttpClients.custom().build();

			httppost = new HttpPost(url);
			httppost.setConfig(requestConfig);
			httppost.setHeader("content-type", "application/json");
			if(header!=null){
				for (Entry<String, String> entry : header.entrySet()) {
					httppost.setHeader(entry.getKey(), entry.getValue());
				}
			}
			StringEntity entity=new StringEntity(body);
			httppost.setEntity(entity);

			HttpResponse response = client.execute(httppost);

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				return EntityUtils.toString(response.getEntity());
			} else {
				LOG.error("请求失败,url=" + url + ", HttpStatus=" + response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e) {
			LOG.error("请求异常,url=" + url + ", 异常原因 : " + e.getMessage());
		} finally {
			try {
				if (httppost != null) {
					httppost.releaseConnection();
				}
				if (client != null) {
					client.close();
				}
			} catch (Exception e) {
			}
		}

		return null;
	}

	/**
	 * post带文件请求
	 * 
	 * @param url
	 * @param file
	 * @param params
	 * @return
	 */
	public String post(String url, File file, Map<String, Object> params) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		HttpPost httppost = null;
		try {
			RequestConfig requestConfig = createRequestConfig();
			client = HttpClients.custom().build();
			httppost = new HttpPost(url);
			httppost.setConfig(requestConfig);
			// 封装请求参数
			NameValuePair[] array = initParamsToArr(params);
			ContentType contentType = ContentType.create("multipart/form-data", array);
			contentType.withCharset(Consts.UTF_8);
			// 将文件转换成流对象
			FileEntity fileEntity = new FileEntity(file, contentType);
			fileEntity.setContentEncoding("UTF-8");
			httppost.setEntity(fileEntity);
			response = client.execute(httppost);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				return EntityUtils.toString(response.getEntity());
			} else {
				LOG.error("请求失败,url=" + url + ", HttpStatus=" + response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e) {
			LOG.error("请求异常,url=" + url, e);
		} finally {
			try {
				if (httppost != null) {
					httppost.releaseConnection();
				}
				if (client != null) {
					client.close();
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * 上传人脸相片
	 * 
	 * @param url
	 * @param file
	 * @param id
	 * @return
	 */
	public String postFile(String url, File file, Long id) {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		HttpPost httppost = null;
		try {
			RequestConfig requestConfig = createRequestConfig();
			client = HttpClients.custom().build();
			httppost = new HttpPost(url);
			httppost.setConfig(requestConfig);
			// 封装请求参数
			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			multipartEntityBuilder.addBinaryBody("file", file);
			multipartEntityBuilder.addTextBody("id", id.toString());
			httppost.setEntity(multipartEntityBuilder.build());
			response = client.execute(httppost);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				return EntityUtils.toString(response.getEntity());
			} else {
				LOG.error("请求失败,url=" + url + ", HttpStatus=" + response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e) {
			LOG.error("请求异常,url=" + url, e);
		} finally {
			try {
				if (httppost != null) {
					httppost.releaseConnection();
				}
				if (client != null) {
					client.close();
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * HTTP GET 请求调用
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return 返回字符串结果或NULL
	 */
	public String get(String url, Map<String, Object> params) {
		CloseableHttpClient client = null;
		HttpGet httpget = null;
		try {
			RequestConfig requestConfig = createRequestConfig();
			client = HttpClients.custom().build();

			URI uri = buildURI(url, params);
			httpget = new HttpGet(uri);
			httpget.setConfig(requestConfig);

			HttpResponse response = client.execute(httpget);

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				return EntityUtils.toString(response.getEntity());
			} else {
				LOG.error("请求失败,url=" + url + ", HttpStatus=" + response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e) {
			LOG.error("请求异常,url=" + url, e);
		} finally {
			try {
				if (httpget != null) {
					httpget.releaseConnection();
				}
				if (client != null) {
					client.close();
				}
			} catch (Exception e) {
			}
		}

		return null;
	}

	/**
	 * 调用webServices服务
	 * 
	 * @param soapData
	 *            soap包
	 * @param url
	 *            服务地址
	 * @return 返回字符串结果或NULL
	 */
	public String requestWebService(String soapData, String url) {
		CloseableHttpClient client = null;
		HttpPost httppost = null;
		try {
			RequestConfig requestConfig = createRequestConfig();

			client = HttpClients.custom().build();

			httppost = new HttpPost(url);
			httppost.setConfig(requestConfig);

			byte[] b = soapData.getBytes(Consts.UTF_8);
			InputStream is = new ByteArrayInputStream(b, 0, b.length);
			InputStreamEntity inputStreamEntity = new InputStreamEntity(is, ContentType.TEXT_XML);
			httppost.setEntity(inputStreamEntity);

			HttpResponse response = client.execute(httppost);

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				return EntityUtils.toString(response.getEntity());
			} else {
				LOG.error("请求失败,url=" + url + ", HttpStatus=" + response.getStatusLine().getStatusCode());
				return null;
			}
		} catch (Exception e) {
			LOG.error("请求异常,url=" + url, e);
		} finally {
			try {
				if (httppost != null) {
					httppost.releaseConnection();
				}
				if (client != null) {
					client.close();
				}
			} catch (Exception e) {
			}
		}

		return null;
	}

	/**
	 * 设置请求配置
	 * 
	 * @return
	 */
	private RequestConfig createRequestConfig() {
		if (requestConfig == null) {
			requestConfig = RequestConfig.custom().setSocketTimeout(this.getSocketTimeout())
					.setConnectTimeout(this.getConnectTimeout()).build();
		}
		return requestConfig;
	}

	/**
	 * 初始化POST请求参数
	 * 
	 * @param params
	 * @return
	 */
	private List<NameValuePair> initParams(Map<String, Object> params) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if ((params != null) && (params.size() > 0) && (params instanceof Map)) {
			Set<Entry<String, Object>> set = params.entrySet();
			Iterator<Entry<String, Object>> iterator = set.iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				nvps.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
			}
		}
		return nvps;
	}

	private NameValuePair[] initParamsToArr(Map<String, Object> params) {
		NameValuePair[] array = null;
		if ((params != null) && (params.size() > 0) && (params instanceof Map)) {
			array = new NameValuePair[params.size()];
			Set<Entry<String, Object>> set = params.entrySet();
			Iterator<Entry<String, Object>> iterator = set.iterator();
			int index = 0;
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				array[index] = new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue()));
				index = index + 1;
			}
		}
		return array;
	}

	/**
	 * 初始化GET请求参数
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 */
	private URI buildURI(String url, Map<String, Object> params) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder(url);
		uriBuilder.setCharset(Consts.UTF_8);
		if (params != null && params instanceof Map) {
			Set<Entry<String, Object>> set = params.entrySet();
			Iterator<Entry<String, Object>> iterator = set.iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				uriBuilder.setParameter(entry.getKey(), String.valueOf(entry.getValue()));
			}
		}
		return uriBuilder.build();
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
