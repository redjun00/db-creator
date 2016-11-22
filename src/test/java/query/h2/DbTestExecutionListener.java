package query.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

@Slf4j
public class DbTestExecutionListener implements TestExecutionListener {
    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        log.debug("call beforeTestClass()");
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        log.debug("call prepareTestInstance()");

    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        log.debug("call beforeTestMethod()");
        prepareDataSet(testContext);
    }

    private void prepareDataSet(TestContext testContext) {
        log.debug("call prepareDataSet()");
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        log.debug("call afterTestMethod()");

    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        log.debug("call afterTestClass()");
    }
}
