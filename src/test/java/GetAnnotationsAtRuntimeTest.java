import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
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
        var clazz = Annotated.class;
        ClassAnnotation annotation = clazz.getAnnotation(ClassAnnotation.class);
        
        assertThat(annotation.value(), is("new"));
    }

    @Test
    public void getAllMethodsAnnotations() {
        List<Annotation> methodAnnotations = Arrays.stream(Annotated.class.getDeclaredMethods())
                .flatMap(method -> Arrays.stream(method.getAnnotations()))
                .collect(Collectors.toList());

        assertThat(methodAnnotations.toString(), is("[@java.lang.Deprecated(forRemoval=true, since=\"12\")]"));
    }

    @Test
    public void getDeprecatedAnnotations_methods() {
        List<Deprecated> deprecatedAnnotations = Arrays.stream(Annotated.class.getDeclaredMethods())
                .map(method -> method.getAnnotation(Deprecated.class))
                .collect(Collectors.toList());

        assertThat(deprecatedAnnotations.size(), is(1));
        Deprecated deprecated = deprecatedAnnotations.get(0);
        
        assertThat(deprecated.forRemoval(), is(true));
        assertThat(deprecated.since(), is("12"));
    }
    
    @Test
    public void getAnnotationsOnSpecificField() throws NoSuchFieldException {
        Annotation[] annotations = Annotated.class.getDeclaredField("count").getAnnotations();
        
        assertThat(Arrays.toString(annotations), is("[@FieldAnnotation()]"));
    }

}
