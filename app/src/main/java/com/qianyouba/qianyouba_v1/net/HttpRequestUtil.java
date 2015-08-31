package com.qianyouba.qianyouba_v1.net;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.qianyouba.qianyouba_v1.common.BaseApplication;
import com.qianyouba.qianyouba_v1.model.IModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
public class HttpRequestUtil {
    private static String TAG = "HttpRequestUtil";
    private BaseApplication app;
    private HttpRequestListener listener;

    public HttpRequestUtil(Context context,IModel model) {
        this.app = (BaseApplication) context.getApplicationContext();
        try {
            listener = (HttpRequestListener) model;
        } catch (Exception e) {
            throw new ClassCastException(context.getClass().getName()
                    + " not implements HttpRequestListener interface!");
        }
    }

    public void stringRequest(final int requestCode, String requestUri,
                              final Map<String, String> params) {
        StringRequest strRequest = new StringRequest(Method.POST, "http://www.baidu.com",
                new Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onResponse(requestCode, response);
                    }
                }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                StringBuffer errorMsg = new StringBuffer("请求失败");
                if (error instanceof NetworkError) {
                    errorMsg.append(",网络异常");
                } else if (error instanceof ParseError) {
                    errorMsg.append(",解析错误");
                } else if (error instanceof ServerError) {
                    errorMsg.append(",服务器异常");
                } else if (error instanceof TimeoutError) {
                    errorMsg.append(",连接超时");
                }
                listener.onErrorResponse(requestCode,
                        errorMsg.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        strRequest.setRetryPolicy(new DefaultRetryPolicy(
                20000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        app.mQueue.add(strRequest);
    }

    public void jsonRequest(final int requestCode, String url,
                            Map<String, String> params) {
        JSONObject jsonObject = new JSONObject(params);
        JsonRequest<JSONObject> jsonRequest = new JsonObjectRequest(
                Method.POST, url, jsonObject,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onResponse(requestCode, response.toString());
                        Log.d(TAG, "response -> " + response.toString());
                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onErrorResponse(requestCode,
                        error.getMessage());
                Log.e(TAG, error.getMessage(), error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");

                return headers;
            }
        };
        app.mQueue.add(jsonRequest);
    }

    public interface HttpRequestListener {
        /**
         * 正确响应
         *
         * @param requestCode
         * @param data
         */
        void onResponse(int requestCode, String data);

        /**
         * 错误响应
         *
         * @param requestCode
         * @param error
         */
        void onErrorResponse(int requestCode, String error);
    }

    private class NormalPostRequest extends Request<JSONObject> {
        private Map<String, String> mMap;
        private Listener<JSONObject> mListener;

        public NormalPostRequest(String url, Listener<JSONObject> listener, ErrorListener errorListener, Map<String, String> map) {
            super(Method.POST, url, errorListener);
            mListener = listener;
            mMap = map;
        }

        //mMap是已经按照前面的方式,设置了参数的实例
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            return mMap;
        }

        //此处因为response返回值需要json数据,和JsonObjectRequest类一样即可
        @Override
        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
            try {
                String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

                return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            } catch (JSONException je) {
                return Response.error(new ParseError(je));
            }
        }

        @Override
        protected void deliverResponse(JSONObject response) {
            mListener.onResponse(response);
        }
    }
}
