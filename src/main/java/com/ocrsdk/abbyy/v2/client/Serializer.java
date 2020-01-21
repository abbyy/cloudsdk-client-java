// Copyright © 2019 ABBYY Production LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.ocrsdk.abbyy.v2.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Serializer {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static String toQueryString(Object object) {
        if (object == null) {
            return "";
        }

        Field[] fields = object.getClass().getDeclaredFields();
        FieldDictionary fieldDictionary = new FieldDictionary();
        for (Field field : fields) {
             String fieldName = getFieldName(field);
             Object fieldValue = getFieldValue(field, object);
             String strFieldValue = getStringValue(fieldValue);

             if (strFieldValue == null || strFieldValue.isEmpty()) {
                 continue;
             }

             fieldDictionary.putField(fieldName, strFieldValue);
        }
        return fieldDictionary.toString();
    }

    private static String getStringValue(Object o) {
        if (o instanceof String) {
            return (String)o;
        }
        else if (o instanceof Iterable) {
            return getCollectionValues((Iterable)o);
        }
        else if (o instanceof Date) {
            return dateFormat.format((Date)o);
        }
        else if (o instanceof Object[]) {
            return getArrayValues((Object[])o);
        }else {
            return o == null ? null : o.toString();
        }
    }

    private static String getCollectionValues(Iterable<?> iterable) {
        List<String> values = new ArrayList<>();
        for (Object element : iterable) {
            String value = element.toString();
            values.add(value);
        }
        return String.join(",", values);
    }

    private static <T> String getArrayValues(T[] array) {
        List<String> values = new ArrayList<>();
        for (T element : array) {
            String value = element.toString();
            values.add(value);
        }
        return String.join(",", values);
    }

    private static String getFieldName(Field field) {
        JsonProperty jsonProperty = field.getAnnotation(JsonProperty.class);
        return jsonProperty == null ? field.getName() : jsonProperty.value();
    }

    private static Object getFieldValue(Field field, Object object) {
        try {
            boolean access = field.isAccessible();
            field.setAccessible(true);
            Object fieldValue = field.get(object);
            field.setAccessible(access);
            return fieldValue;
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class FieldDictionary {
        private List<String> pairs = new ArrayList<>();

        public void putField(String name, String value) {
            try {
                pairs.add(urlEncode(name) + "=" + urlEncode(value));
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return String.join("&", pairs);
        }

        private String urlEncode(String s) throws UnsupportedEncodingException {
            return URLEncoder.encode(s, StandardCharsets.UTF_8.toString());
        }
    }
}
