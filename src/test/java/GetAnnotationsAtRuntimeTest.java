import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2019-02-19.
 */
public class GetAnnotationsAtRuntimeTest {

    @Test
    public void getClassAnnotations() {
        Class<Annotated> clazz = Annotated.class;
        Annotation[] annotations = clazz.getAnnotations();

        assertThat(Arrays.toString(annotations), is("[@ClassAnnotation(value=\"new\")]"));
    }

    @Test
    public void getClassAnnotations_asObject() {
        Class<Annotated> clazz = Annotated.class;
        var annotation = clazz.getAnnotation(ClassAnnotation.class);
        assertThat(annotation.value(), is("new"));
    }

    @Test
    public void getMethodAnnotations() {
        var methodAnnotations = Arrays.stream(Annotated.class.getDeclaredMethods())
                .flatMap(method -> Arrays.stream(method.getAnnotations()))
                .collect(Collectors.toList());

        assertThat(methodAnnotations.toString(), is("[@java.lang.Deprecated(forRemoval=true, since=\"12\")]"));
    }

    @Test
    public void getDeprecatedAnnotations_methods() {
        var deprecatedAnnotations = Arrays.stream(Annotated.class.getDeclaredMethods())
                .map(method -> method.getAnnotation(Deprecated.class))
                .collect(Collectors.toList());

        assertThat(deprecatedAnnotations.size(), is(1));
        var deprecated = deprecatedAnnotations.get(0);
        assertThat(deprecated.forRemoval(), is(true));
        assertThat(deprecated.since(), is("12"));
    }

}
