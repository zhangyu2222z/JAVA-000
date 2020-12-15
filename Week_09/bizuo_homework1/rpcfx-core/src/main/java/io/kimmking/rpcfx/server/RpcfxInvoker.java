package io.kimmking.rpcfx.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.api.RpcfxResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RpcfxInvoker {

    private RpcfxResolver resolver;

    public RpcfxInvoker(RpcfxResolver resolver){
        this.resolver = resolver;
    }

    public RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse();
        String serviceClass = request.getServiceClass();
        // 作业1：改成泛型和反射
        Object result = customReflaction(serviceClass, request.getMethod(), request.getParams());
        response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
        response.setStatus(true);
        return response;
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }

    private <T> Object customReflaction (String className, String methodName, Object[] params){
        Object result = null;
        try {
            Class<T> clazz = (Class<T>) Class.forName(className);
            Method[] declaredMethods = clazz.getDeclaredMethods();
            T instance = clazz.newInstance();
            if (declaredMethods != null && declaredMethods.length > 0) {
                for (Method method : declaredMethods) {
                    if (method.getName().equals(methodName)) {
                        result = method.invoke(instance, params);
                        break;
                    }
                }
            }
            return result;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return e;
        }
    }


}
