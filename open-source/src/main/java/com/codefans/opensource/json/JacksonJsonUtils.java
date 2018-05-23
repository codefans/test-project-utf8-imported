package com.codefans.opensource.json;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: codefans
 * @date: 2018-05-23 09:07
 * jackson json utils
 */
public class JacksonJsonUtils {

    private static final Logger LOG = LoggerFactory.getLogger(JacksonJsonUtils.class);
    public static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
    static ObjectMapper objectMapper = new ObjectMapper();

    public JacksonJsonUtils() {
    }

    public static Map<String, Object> readValue(String josnStr) throws RuntimeException {
        try {
            return (Map)objectMapper.readValue(josnStr, HashMap.class);
        } catch (JsonParseException var2) {
            LOG.error(var2.getMessage(), var2);
            throw new RuntimeException(var2);
        } catch (JsonMappingException var3) {
            LOG.error(var3.getMessage(), var3);
            throw new RuntimeException(var3);
        } catch (IOException var4) {
            LOG.error(var4.getMessage(), var4);
            throw new RuntimeException(var4);
        }
    }

    public static <T> T readValue(String josnStr, Class<T> cls) throws RuntimeException {
        try {
            return objectMapper.readValue(josnStr, cls);
        } catch (JsonParseException var3) {
            LOG.error(var3.getMessage(), var3);
            throw new RuntimeException(var3);
        } catch (JsonMappingException var4) {
            LOG.error(var4.getMessage(), var4);
            throw new RuntimeException(var4);
        } catch (IOException var5) {
            LOG.error(var5.getMessage(), var5);
            throw new RuntimeException(var5);
        }
    }

    public static <T> T readValue(String josnStr, TypeReference<T> t) throws RuntimeException {
        try {
            return objectMapper.readValue(josnStr, t);
        } catch (JsonParseException var3) {
            LOG.error(var3.getMessage(), var3);
            throw new RuntimeException(var3);
        } catch (JsonMappingException var4) {
            LOG.error(var4.getMessage(), var4);
            throw new RuntimeException(var4);
        } catch (IOException var5) {
            LOG.error(var5.getMessage(), var5);
            throw new RuntimeException(var5);
        }
    }

    public static <T> T readValue(String josnStr, JavaType t) throws RuntimeException {
        try {
            return objectMapper.readValue(josnStr, t);
        } catch (JsonParseException var3) {
            LOG.error(var3.getMessage(), var3);
            throw new RuntimeException(var3);
        } catch (JsonMappingException var4) {
            LOG.error(var4.getMessage(), var4);
            throw new RuntimeException(var4);
        } catch (IOException var5) {
            LOG.error(var5.getMessage(), var5);
            throw new RuntimeException(var5);
        }
    }

    public static <T> T readValue(InputStream josnStr, Class<T> cls) throws RuntimeException {
        try {
            return objectMapper.readValue(josnStr, cls);
        } catch (JsonParseException var3) {
            LOG.error(var3.getMessage(), var3);
            throw new RuntimeException(var3);
        } catch (JsonMappingException var4) {
            LOG.error(var4.getMessage(), var4);
            throw new RuntimeException(var4);
        } catch (IOException var5) {
            LOG.error(var5.getMessage(), var5);
            throw new RuntimeException(var5);
        }
    }

    public static <T> T readValue(InputStream josnStr, TypeReference<T> t) throws RuntimeException {
        try {
            return objectMapper.readValue(josnStr, t);
        } catch (JsonParseException var3) {
            LOG.error(var3.getMessage(), var3);
            throw new RuntimeException(var3);
        } catch (JsonMappingException var4) {
            LOG.error(var4.getMessage(), var4);
            throw new RuntimeException(var4);
        } catch (IOException var5) {
            LOG.error(var5.getMessage(), var5);
            throw new RuntimeException(var5);
        }
    }

    public static <T> T readValue(InputStream josnStr, JavaType t) throws RuntimeException {
        try {
            return objectMapper.readValue(josnStr, t);
        } catch (JsonParseException var3) {
            LOG.error(var3.getMessage(), var3);
            throw new RuntimeException(var3);
        } catch (JsonMappingException var4) {
            LOG.error(var4.getMessage(), var4);
            throw new RuntimeException(var4);
        } catch (IOException var5) {
            LOG.error(var5.getMessage(), var5);
            throw new RuntimeException(var5);
        }
    }

    public static String writeValue(Object entity) throws RuntimeException {
        return writeValue(entity, false);
    }

    public static String writeValue(Object entity, boolean prettyPrint) throws RuntimeException {
        try {
            StringWriter e = new StringWriter();
            JsonGenerator jg = objectMapper.getJsonFactory().createJsonGenerator(e);
            if(prettyPrint) {
                jg.useDefaultPrettyPrinter();
            }

            objectMapper.writeValue(jg, entity);
            return e.toString();
        } catch (JsonGenerationException var4) {
            LOG.error(var4.getMessage(), var4);
            throw new RuntimeException(var4);
        } catch (JsonMappingException var5) {
            LOG.error(var5.getMessage(), var5);
            throw new RuntimeException(var5);
        } catch (IOException var6) {
            LOG.error(var6.getMessage(), var6);
            throw new RuntimeException(var6);
        }
    }

    public static String getJsonStr(int count) {
        return "{\"result\":" + count + " }";
    }

    public static JsonFactory getJsonFactory() {
        return objectMapper.getJsonFactory();
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * 格式化json串
     * @param jsonStr
     * @return
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '"':
                    if (last != '\\') {
                        isInQuotationMarks = !isInQuotationMarks;
                    }
                    sb.append(current);
                    break;

                case '{':
                case '[':
                    sb.append(current);
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent++;
                        addIndentBlank(sb, indent);
                    }
                    break;

                case '}':
                case ']':
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent--;
                        addIndentBlank(sb, indent);
                    }
                    sb.append(current);
                    break;

                case ',':
                    sb.append(current);
                    if (last != '\\' && !isInQuotationMarks) {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;

                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     * @param sb
     * @param indent
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }

    public static void main(String[] args) {
    }

    static {
        objectMapper.disable(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
        objectMapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS);
        objectMapper.enable(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER);
        objectMapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
        objectMapper.setDateFormat(new SimpleDateFormat(DATEFORMAT));
    }

}
