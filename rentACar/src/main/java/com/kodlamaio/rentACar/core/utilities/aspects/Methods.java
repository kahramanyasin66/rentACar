package com.kodlamaio.rentACar.core.utilities.aspects;


import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public final class Methods {

    public static List<String> getParameterNames(Method method) {
        Parameter[] parameters = method.getParameters();
        List<String> parameterNames = new ArrayList<>();

        for (Parameter parameter : parameters) {
            if(!parameter.isNamePresent()) {
                throw new IllegalArgumentException("Parameter names are not present!");
            }

            String parameterName = parameter.getName();
            parameterNames.add(parameterName);
        }

        return parameterNames;
    }

    private Methods(){}
}