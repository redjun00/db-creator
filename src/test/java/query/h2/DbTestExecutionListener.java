package query.h2;

import lombok.extern.slf4j.Slf4j;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseDataSet;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
public class DbTestExecutionListener implements TestExecutionListener {

    private final String CUSTOMER_INSERT_XML_FILE_PATH = "db/sql/customer_insert.xml";
    private IDataSet tableDataSet;

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        log.debug("call beforeTestClass()");
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        log.debug("call prepareTestInstance()");
        prepareDataSet(testContext);
    }

    private ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

    private void prepareDataSet(TestContext testContext) throws DatabaseUnitException, SQLException {
        log.debug("call prepareDataSet()");

        DatabaseDataSet databaseDataSet = new DatabaseDataSet(getConnection(testContext), true);

        tableDataSet = new FlatXmlDataSetBuilder()
                .setMetaDataSet(databaseDataSet)
                .build(contextClassLoader.getResourceAsStream(CUSTOMER_INSERT_XML_FILE_PATH));
    }

    private IDatabaseConnection getConnection(TestContext testContext) throws SQLException, DatabaseUnitException {
        DataSource dataSource = testContext.getApplicationContext().getBean(DataSource.class);
        IDatabaseConnection connection = new DatabaseConnection(dataSource.getConnection());
        connection.getConfig().setProperty(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true);
        connection.getConfig().setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);
        connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
        return connection;
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        log.debug("call beforeTestMethod()");
        IDatabaseConnection connection = getConnection(testContext);
        cleanAndInsertDb(connection);

    }

    private void cleanAndInsertDb(IDatabaseConnection connection) throws DatabaseUnitException, SQLException {
        DatabaseOperation.CLEAN_INSERT.execute(connection, tableDataSet);
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
