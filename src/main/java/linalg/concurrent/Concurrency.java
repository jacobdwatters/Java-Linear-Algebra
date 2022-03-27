package linalg.concurrent;


/**
 * Class for constants and basic methods used for concurrency.
 */
class Concurrency {
    private Concurrency() {
        throw new IllegalStateException("Cannot instantiate utility class.");
    }


    /**
     * The number of processors (physical and virtual) available to the Java virtual machine. This value may change
     * during runtime. Use {@link #updateProcessors()} to update the value.
     */
    static int PROCESSORS = Runtime.getRuntime().availableProcessors();


    /**
     * Update the number of processors available to the JVM.
     */
    protected static void updateProcessors() {
        PROCESSORS = Runtime.getRuntime().availableProcessors();
    }
}
