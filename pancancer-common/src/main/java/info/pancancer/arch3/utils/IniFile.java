package info.pancancer.arch3.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This seems to be some representation of an INI file. This is a good candidate for replacement by Apache Commons Configuration
 *
 * @author boconnor
 */
public class IniFile {

    private final Pattern _section = Pattern.compile("\\s*\\[([^]]*)\\]\\s*");
    private final Pattern _keyValue = Pattern.compile("\\s*([^=]*)=(.*)");
    private final Map<String, Map<String, String>> _entries = new HashMap<>();

    public IniFile(String path) throws IOException {
        load(path);
    }

    public final void load(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            String section = "no-section";
            while ((line = br.readLine()) != null) {
                Matcher m = _section.matcher(line);
                if (m.matches()) {
                    section = m.group(1).trim();
                } else if (section != null) {
                    m = _keyValue.matcher(line);
                    if (m.matches()) {
                        String key = m.group(1).trim();
                        String value = m.group(2).trim();
                        Map<String, String> kv = _entries.get(section);
                        if (kv == null) {
                            _entries.put(section, kv = new HashMap<>());
                        }
                        kv.put(key, value);
                    }
                }
            }
        }
    }

    public String getString(String section, String key, String defaultvalue) {
        Map<String, String> kv = _entries.get(section);
        if (kv == null) {
            return defaultvalue;
        }
        return kv.get(key);
    }

    public int getInt(String section, String key, int defaultvalue) {
        Map<String, String> kv = _entries.get(section);
        if (kv == null) {
            return defaultvalue;
        }
        return Integer.parseInt(kv.get(key));
    }

    public float getFloat(String section, String key, float defaultvalue) {
        Map<String, String> kv = _entries.get(section);
        if (kv == null) {
            return defaultvalue;
        }
        return Float.parseFloat(kv.get(key));
    }

    public double getDouble(String section, String key, double defaultvalue) {
        Map<String, String> kv = _entries.get(section);
        if (kv == null) {
            return defaultvalue;
        }
        return Double.parseDouble(kv.get(key));
    }

    public Map<String, Map<String, String>> getEntries() {
        return _entries;
    }
}