
package com.codegroup.desafio;

import com.codegroup.desafio.helper.Helper;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HelperTest {

    private enum TestEnum {
        VALUE_ONE,
        VALUE_TWO
    }

    @Test
    void testEnumToMap() {
        Map<TestEnum, String> result = Helper.enumToMap(TestEnum.class);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("VALUE_ONE", result.get(TestEnum.VALUE_ONE));
        assertEquals("VALUE_TWO", result.get(TestEnum.VALUE_TWO));
    }

    @Test
    void testValidateWithErrors() {
        Object model = new Object();
        ModelMap modelMap = new ModelMap();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        boolean result = Helper.validate(model, modelMap, bindingResult, "testModel");

        assertFalse(result);
        assertTrue(modelMap.containsAttribute("testModel"));
        assertTrue(modelMap.containsAttribute("errors"));
        assertEquals(model, modelMap.get("testModel"));
        assertEquals(bindingResult, modelMap.get("errors"));
    }

    @Test
    void testValidateWithoutErrors() {
        Object model = new Object();
        ModelMap modelMap = new ModelMap();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        boolean result = Helper.validate(model, modelMap, bindingResult, "testModel");

        assertTrue(result);
        assertFalse(modelMap.containsAttribute("testModel"));
        assertFalse(modelMap.containsAttribute("errors"));
    }
}
