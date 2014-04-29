package jpower.core.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AlphabetTest.class,
        ConditionTest.class,
        ConfigurationTest.class,
        FactoryTest.class,
        InternalTest.class,
        IOUtilsTest.class,
        LoadTest.class,
        MultiMapTest.class,
        PowerLoaderTest.class,
        RandomTest.class,
        ThreadUtilsTest.class,
        ByteUtilsTest.class,
        WorkerTest.class,
        GenericSnifferTest.class,
        OperatingSystemTest.class,
        CallUtilsTest.class,
        ServiceTest.class,
        WrapperTest.class,
        RepeaterTest.class,
        ConditionalAdapterTest.class,
        StringUtilsTest.class
})
public class CoreTestSuite
{
}