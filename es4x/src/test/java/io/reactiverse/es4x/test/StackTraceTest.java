package io.reactiverse.es4x.test;

import io.reactiverse.es4x.Runtime;
import io.reactiverse.es4x.impl.AsyncError;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.reactiverse.es4x.test.JS.commonjs;

@RunWith(VertxUnitRunner.class)
public class StackTraceTest {

  private Runtime runtime;

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void initialize() {
    runtime = commonjs(rule.vertx());
  }

  @Test(timeout = 10000)
  public void shouldGenerateUsefulStackTrace(TestContext should) {
    final Async test = should.async();
    // pass the assertion to the engine
    runtime.put("should", should);
    runtime.put("test", test);

    runtime.eval("require('./stacktraces')");
  }

  @Test(timeout = 10000)
  public void shouldGenerateUsefulStackTraceFromJS(TestContext should) {
    final Async test = should.async();
    // pass the assertion to the engine
    runtime.put("should", should);
    runtime.put("test", test);

    runtime.eval("require('./stacktraces/jserror')");
  }

  @Test
  public void shouldReturnNullWhenNull(TestContext should) {
    should.assertNull(AsyncError.combine((Throwable) null, null));
  }

  @Test
  public void shouldReturnThrowableWhenNotNull(TestContext should) {
    should.assertTrue(AsyncError.combine(new RuntimeException("Oops!"), null) instanceof Throwable);
  }
}
