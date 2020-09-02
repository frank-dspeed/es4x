package io.reactiverse.es4x.test;

import io.reactiverse.es4x.Runtime;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.reactiverse.es4x.test.JS.commonjs;

@RunWith(VertxUnitRunner.class)
public class JSONTest {

  private Runtime runtime;

  @Rule
  public RunTestOnContext rule = new RunTestOnContext();

  @Before
  public void initialize() {
    runtime = commonjs(rule.vertx());
  }

  @Test
  public void shouldStringify() {
    runtime.eval("console.log(JSON.stringify({}))");
    runtime.eval("console.log(JSON.stringify([]))");
    runtime.eval("console.log(JSON.stringify({K:1}))");
    runtime.eval("console.log(JSON.stringify([1,2,'', null]))");
    // these tests show the interop
    runtime.eval("var JsonObject = Java.type('io.vertx.core.json.JsonObject'); console.log(JSON.stringify(new JsonObject()))");
    runtime.eval("var JsonObject = Java.type('io.vertx.core.json.JsonObject'); var x = new JsonObject(); x['k'] = 1; console.log(JSON.stringify(x))");
    runtime.eval("var JsonObject = Java.type('io.vertx.core.json.JsonObject'); var x = new JsonObject(); x.k = 1; console.log(JSON.stringify(x))");
  }
}
