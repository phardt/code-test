package com.codegroup.desafio.helper;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Helper {

    private static final String MODEL_ERRORS = "errors";

    private Helper() {
    }

    public static <T extends Enum<T>> Map<T, String> enumToMap(Class<T> enumClass) {
        final Map<T, String> map = new LinkedHashMap<>();
        Stream.of(enumClass.getEnumConstants())
                .forEach(item -> map.put(item, item.toString()));
        return map;
    }

    public static <T> boolean validate(final T model,
                                       final ModelMap modelMap,
                                       final BindingResult bindingResult,
                                       final String modelName){
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute(modelName, model);
            modelMap.addAttribute(MODEL_ERRORS, bindingResult);
            return false;
        }

        return true;
    }
}
