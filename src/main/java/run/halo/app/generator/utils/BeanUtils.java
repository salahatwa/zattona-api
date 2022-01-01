package run.halo.app.generator.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class BeanUtils {
    /**
     * åˆ¤æ–­å¯¹è±¡æ˜¯å�¦ä¸ºç©º
     *
     * @param o java.lang.Object.
     * @return boolean.
     */
    @SuppressWarnings("unused")
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            return ((String) o).trim().length() == 0;
        } else if (o instanceof Collection) {
            return ((Collection) o).isEmpty();
        } else if (o.getClass().isArray()) {
            return ((Object[]) o).length == 0;
        } else if (o instanceof Map) {
            return ((Map) o).isEmpty();
        } else if (o instanceof Optional) {
            return !((Optional) o).isPresent();
        }
        return false;
    }

    /**
     * åˆ¤æ–­å¯¹è±¡æ˜¯å�¦ä¸ºç©ºï¼Œä¸ºç©ºå°±è¿”å›žæŒ‡å®šçš„é»˜è®¤å¯¹è±¡
     *
     * @param object        éœ€è¦�åˆ¤æ–­çš„å¯¹è±¡
     * @param defaultObject ä¸ºç©ºæ—¶ä½¿ç”¨çš„é»˜è®¤å¯¹è±¡
     * @param <T>           ç±»åž‹
     * @return æŒ‡å®šçš„ç±»åž‹çš„é�žç©ºå¯¹è±¡
     */
    public static <T> T isEmpty(T object, T defaultObject) {
        boolean isEmptyBoolean = isEmpty(object);
        if (isEmptyBoolean) {
            return defaultObject;
        } else {
            return object;
        }
    }
}

