package cn.xyr.ssm.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * http工具类
 */
public class HttpUtils {


    /**
     * 模拟表单发起post请求
     *
     * @param url 请求url
     * @param map 参数map
     * @return 请求结果
     */
    public static String postWithParamsForString(String url, Map<String, String> map) {
        List<NameValuePair> params = new ArrayList<>();
        if (map != null) {
            for (String key : map.keySet()) {
                params.add(new BasicNameValuePair(key, map.get(key)));
            }
        }
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        String s = "";
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            HttpResponse response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                s = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}