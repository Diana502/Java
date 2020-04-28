package diana.maven.project.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Suna
 * @since 2020/4/28 12:52
 */
public class ExceptionUtil {

    public static String toString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }

}
