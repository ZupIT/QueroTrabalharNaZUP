package br.com.zup.recruiting.ramonfacchinzip.aspect.annotation.apidoc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Rest Controllers annotated with @{@link ExposeApiDocumentation}
 * will automatically be added to Swagger API Documentation.
 * @author ramonfacchin
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExposeApiDocumentation {

}
