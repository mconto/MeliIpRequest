package com.localization.ip.util;

import java.util.regex.Pattern;

public class ValidateIp {

    private static final String IP_PATTERN =
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final Pattern pattern = Pattern.compile(IP_PATTERN);

    public static boolean esIpValida(String ip) {
        return pattern.matcher(ip).matches();
    }
}
