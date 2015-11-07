import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class OSDependent implements TestRule {
    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RunOn env = description.getAnnotation(RunOn.class);
                if (env == null || canEvaluate(env.value())) {
                    base.evaluate();
                } else {
                    // don't evaluate
                }
            }
        };
    }

    private boolean canEvaluate(RunOn.OS os) {
        String osName = System.getProperty("os.name");
        if (osName == null) return false;
        if (os == RunOn.OS.WINDOWS && osName.startsWith("Windows")) return true;
        if (os == RunOn.OS.MAC && osName.startsWith("Mac OS X")) return true;
        if (os == RunOn.OS.LINUX && osName.startsWith("Linux")) return true;
        return false;
    }
}
