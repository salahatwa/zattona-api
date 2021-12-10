package run.halo.app.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Version utility.
 *
 * @author ssatwa
 * @date 2020-08-03
 */
@Slf4j
public final class VersionUtil {

    private VersionUtil() {
    }

    /**
     * Compare version.
     *
     * @param current current version.
     * @param require require version.
     * @return true or false.
     */
    public static boolean compareVersion(String current, String require) {
        Version leftVersion = Version.resolve(current).orElse(Version.emptyVersion());
        Version rightVersion = Version.resolve(require).orElse(Version.emptyVersion());
        return leftVersion.compareTo(rightVersion) >= 0;
    }

}
