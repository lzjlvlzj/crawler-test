package org.ack.crawler.hct.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.FormBodyPartBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	static HttpClient httpClient = HttpClients.createDefault();

	private static HttpResponse invoke(HttpUriRequest httpRqeust) {
		HttpResponse response = null;
		try {
			response = httpClient.execute(httpRqeust);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * 处理上传文件
	 * 
	 * @param url
	 * @param params
	 * @param b
	 * @return
	 */
	public static String MethodPost(String url, Map<String, String> params,
			byte[] b) {
		HttpPost httpPost = new HttpPost(url);
		MultipartEntityBuilder meb = MultipartEntityBuilder.create();
		meb.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			meb.addTextBody(key, params.get(key),ContentType.TEXT_PLAIN.withCharset("UTF-8"));
		}
		ContentBody cb = new ByteArrayBody(b,"file");//
		FormBodyPartBuilder fbpb = FormBodyPartBuilder.create();
		fbpb.setBody(cb);
		fbpb.setName("xmlFile");
		meb.addPart(fbpb.build());
		//meb.addBinaryBody("xmlFile", b);  //?? bug?内部ByteArrayBody中filename为null(实际不能为null)
		HttpEntity he = meb.build();
		httpPost.setEntity(he);
		HttpResponse response = invoke(httpPost);
		return parse(response);
	}

	/**
	 * 普通post请求
	 * 
	 * @param params
	 */
	public static String MethodPost(String url, Map<String, String> params) {
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Set<String> keySet = params.keySet();
		HttpEntity he = null;
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		try {
			he = new UrlEncodedFormEntity(nvps, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		httpPost.setEntity(he);
		HttpResponse response = invoke(httpPost);
		return parse(response);
	}
	public static String MethodGet(String url, Header[] headers) {
		HttpGet httpGet = new HttpGet(url);
		if(null != headers){
			httpGet.setHeaders(headers);
		}
		HttpResponse response = invoke(httpGet);
		return parse(response);
	}

	public static String MethodGet(String url) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpGet.setHeader("Cache-Control", "max-age=0");
		httpGet.setHeader("Connection", "keep-alive");
		httpGet.setHeader("Host", "www.qu.la");
		httpGet.setHeader("Cookie", "obj=1; bdshare_firstime=1479270000437; PPad_id_PP=1");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
		
		HttpResponse response = invoke(httpGet);
		String s = parse(response);
		try {
			s = new String(s.getBytes("iso-8859-1"),"gbk");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}

	private static String parse(HttpResponse response) {
		HttpEntity entity = response.getEntity();
		String s = null;
		try {
			s = EntityUtils.toString(entity);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	public void close() {

	}
}
