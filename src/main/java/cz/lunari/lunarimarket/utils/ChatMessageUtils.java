package cz.lunari.lunarimarket.utils;

import cz.lunari.lunarimarket.LunariMarket;

public class ChatMessageUtils {

    public static String translateAlternateColorCodes(final char altColorChar, final String textToTranslate) {
        final StringBuilder b = new StringBuilder();
        final char[] mess = textToTranslate.toCharArray();
        boolean color = false, hashtag = false, doubleTag = false;
        char tmp;

        for (int i = 0; i < mess.length; ) {
            final char c = mess[i];
            if (doubleTag) {
                doubleTag = false;
                final int max = i + 3;
                if (max <= mess.length) {
                    boolean match = true;
                    for (int n = i; n < max; n++) {
                        tmp = mess[n];
                        if (!((tmp >= '0' && tmp <= '9') || (tmp >= 'a' && tmp <= 'f') || (tmp >= 'A' && tmp <= 'F'))) {
                            match = false;
                            break;
                        }
                    }

                    if (match) {
                        b.append(net.md_5.bungee.api.ChatColor.COLOR_CHAR);
                        b.append('x');
                        for (; i < max; i++) {
                            tmp = mess[i];
                            b.append(net.md_5.bungee.api.ChatColor.COLOR_CHAR);
                            b.append(tmp);
                            b.append(net.md_5.bungee.api.ChatColor.COLOR_CHAR);
                            b.append(tmp);
                        }

                        continue;
                    }
                }

                b.append(altColorChar);
                b.append("##");
            }

            if (hashtag) {
                hashtag = false;

                if (c == '#') {
                    doubleTag = true;
                    i++;
                    continue;
                }

                final int max = i + 6;

                if (max <= mess.length) {
                    boolean match = true;

                    for (int n = i; n < max; n++) {
                        tmp = mess[n];
                        if (!((tmp >= '0' && tmp <= '9') || (tmp >= 'a' && tmp <= 'f') || (tmp >= 'A' && tmp <= 'F'))) {
                            match = false;
                            break;
                        }
                    }

                    if (match) {
                        b.append(net.md_5.bungee.api.ChatColor.COLOR_CHAR);
                        b.append('x');

                        for (; i < max; i++) {
                            b.append(net.md_5.bungee.api.ChatColor.COLOR_CHAR);
                            b.append(mess[i]);
                        }
                        continue;
                    }
                }

                b.append(altColorChar);
                b.append('#');
            }


            if (color) { // Color module
                color = false;

                if (c == '#') {
                    hashtag = true;
                    i++;
                    continue;
                }

                if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || c == 'r' || (c >= 'k' && c <= 'o') || (c >= 'A' && c <= 'F') || c == 'R' || (c >= 'K' && c <= 'O')) {
                    b.append(net.md_5.bungee.api.ChatColor.COLOR_CHAR);
                    b.append(c);
                    i++;
                    continue;
                }

                b.append(altColorChar);
            }

            if (c == altColorChar) { // c == '&'
                color = true;
                i++;
                continue;
            }

            b.append(c);
            i++;

        }

        if (color)
            b.append(altColorChar);
        else if (hashtag) {
            b.append(altColorChar);
            b.append('#');
        } else if (doubleTag) {
            b.append(altColorChar);
            b.append("##");
        }

        return b.toString();
    }


    public static String translateColors(String message) {
        return translateAlternateColorCodes('&', message);
    }

    /* Console send method */
    public static void logConsole(String message) {
        LunariMarket.getInstance()
                .getServer()
                .getConsoleSender()
                .sendMessage("[LunariMarket] " + translateColors(message));
    }
}
