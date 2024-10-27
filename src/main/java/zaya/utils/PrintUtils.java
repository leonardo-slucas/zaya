package zaya.utils;

import br.com.mitra.actionJar.ActionContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class PrintUtils {
    private static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final String PRINT_TITLE = "[Zaya] Kmeans Clustering | ";
    private static final String DEBUG_TITLE = "DEBUG | ";
    private static final String INFO_TITLE = "INFO  | ";
    private static final String SERVICE_TITLE = "Service | ";

    public static Boolean debug;

    public static void setDebug(ActionContext actionContext) {
        if (actionContext == null || actionContext.getSourceContent().isEmpty()) {
            PrintUtils.debug = Boolean.FALSE;
            return;
        }
        String debugString = (String) actionContext.getSourceContent().stream().findFirst().get().getField("debug");
        PrintUtils.debug = debugString == null ? Boolean.FALSE
                : debugString.equals("true") ? Boolean.TRUE : Boolean.FALSE;
    }

    public static void print(String message) {
        Logger.getLogger("A2").info("| " + LocalDateTime.now().format(DATE) + " | "
                + INFO_TITLE + PRINT_TITLE + SERVICE_TITLE + message);
    }

    public static void duration(String message, Long executionStart) {
        Logger.getLogger("A2").info("| " + LocalDateTime.now().format(DATE) + " | "
                        + INFO_TITLE
                        + PRINT_TITLE
                        + SERVICE_TITLE
                        + message + " Duration: "
                        + String.format("%.3f",
                                Double.parseDouble(String.valueOf(System.currentTimeMillis() - executionStart)) / 1000)
                        + "s");
    }

    public static void printDebug(String message) {
        if (PrintUtils.debug)
            Logger.getLogger("A2").info("| " + LocalDateTime.now().format(DATE) + " | "
                    + DEBUG_TITLE
                    + PRINT_TITLE
                    + SERVICE_TITLE
                    + message);
    }

    public static void durationDebug(String message, Long executionStart) {
        if (PrintUtils.debug)
            Logger.getLogger("A2").info("| " + LocalDateTime.now().format(DATE) + " | "
                    + DEBUG_TITLE + PRINT_TITLE
                    + SERVICE_TITLE
                    + message
                    + ". Duration: "
                    + String.format("%.3f",
                            Double.parseDouble(String.valueOf(System.currentTimeMillis() - executionStart)) / 1000)
                    + "s");
    }

    public static void printError(String message) {
        Logger.getLogger("A2").info("| " + LocalDateTime.now().format(DATE) + " | "
                + " [ERROR] "
                + PRINT_TITLE
                + SERVICE_TITLE
                + message);
    }
}
