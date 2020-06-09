package diana.maven.project.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Suna
 * @since 2020/4/28 12:52
 */
public class ExceptionUtils {

    public static String toString(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }

}
