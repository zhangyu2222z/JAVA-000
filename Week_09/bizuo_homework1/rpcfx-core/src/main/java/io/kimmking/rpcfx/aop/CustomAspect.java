package io.kimmking.rpcfx.aop;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author zhangy
 * @version 3.0
 * @description:
 * @email: zhangy2222z@sina.cn
 * @create 2020-12-15 15:23
 **/
@Component
@Aspect
public class CustomAspect {

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");



//    final Class<T> serviceClass, final String url
    @Around("execution(* io.kimmking.rpcfx.client.Rpcfx.create(..))")
    public Object run(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法参数值数组
        Object[] args = joinPoint.getArgs();
//        //得到其方法签名
//        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//        //获取方法参数类型数组
//        Class[] paramTypeArray = methodSignature.getParameterTypes();
//        if (EntityManager.class.isAssignableFrom(paramTypeArray[paramTypeArray.length - 1])) {
//            //如果方法的参数列表最后一个参数是entityManager类型，则给其赋值
//            args[args.length - 1] = entityManager;
//        }
//        logger.info("请求参数为{}",args);
//        //动态修改其参数
//        //注意，如果调用joinPoint.proceed()方法，则修改的参数值不会生效，必须调用joinPoint.proceed(Object[] args)
//        Object result = joinPoint.proceed(args);
//        logger.info("响应结果为{}",result);
//        //如果这里不返回result，则目标对象实际返回值会被置为null
        RpcfxRequest request = new RpcfxRequest();
        Class clazz = (Class)args[0];
        request.setServiceClass(clazz.getName());
        Method[] declaredMethods = clazz.getDeclaredMethods();
        request.setMethod(declaredMethods[0].getName());
        request.setParams(args);
        RpcfxResponse response = post(request, (String)args[1]);
        return response;
    }

    private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        HttpPost httppost = new HttpPost(url);
        StringEntity requestEntity = new StringEntity(JSON.toJSONString(req),"utf-8");
        requestEntity.setContentEncoding("UTF-8");
        httppost.setHeader("Content-type", "application/json");
        httppost.setEntity(requestEntity);
        String execute = client.execute(httppost, responseHandler);
        return JSON.parseObject(execute, RpcfxResponse.class);

//        String reqJson = JSON.toJSONString(req);
//        // 2.尝试使用httpclient或者netty client
//        OkHttpClient client = new OkHttpClient();
//        final Request request = new Request.Builder()
//                .url(url)
//                .post(RequestBody.create(JSONTYPE, reqJson))
//                .build();
//        String respJson = client.newCall(request).execute().body().string();
//        System.out.println("resp json: "+respJson);
//        return JSON.parseObject(respJson, RpcfxResponse.class);
    }

}
