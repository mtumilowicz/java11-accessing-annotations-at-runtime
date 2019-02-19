[![Build Status](https://travis-ci.com/mtumilowicz/java11-accessing-annotations-at-runtime.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-accessing-annotations-at-runtime)

# java11-accessing-annotations-at-runtime
_Reference_: https://www.amazon.com/Java-Language-Features-Modules-Expressions/dp/1484233476  
_Reference_: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/reflect/AnnotatedElement.html

# preface
* https://github.com/mtumilowicz/java-annotations-retention-policy
* https://github.com/mtumilowicz/java-reflection
* we are able to access only runtime retention policy annotations
* annotated elements (like field, or class) are represented in Java
by `java.lang.reflect.AnnotatedElement` interface
* classes that implements `AnnotatedElement`:
    * `AccessibleObject`
    * `Class`
    * `Constructor`
    * `Executable`
    * `Field`
    * `Method`
    * `Module`
    * `Package`
    * `Parameter`

# project description
1. annotations
    ```
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented()
    @interface ClassAnnotation {
        String value();
    }
    ```
    ```
    @Target(ElementType.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface FieldAnnotation {
    }
    ```
1. class for test purposes
    ```
    @ClassAnnotation("new")
    class Annotated {
        
        @FieldAnnotation
        private int count;
        
        @Deprecated(forRemoval = true, since = "12")
        void go() {
        }
    }
    ```
1. tests
    * get class annotations
        ```
        Class<Annotated> clazz = Annotated.class;
        Annotation[] annotations = clazz.getAnnotations();
        
        assertThat(Arrays.toString(annotations), is("[@ClassAnnotation(value=\"new\")]"));
        ```
    * get annotation as a concrete class
        ```
        var clazz = Annotated.class;
        ClassAnnotation annotation = clazz.getAnnotation(ClassAnnotation.class);
        
        assertThat(annotation.value(), is("new"));
        ```
    * get all method annotations
        ```
        List<Annotation> methodAnnotations = Arrays.stream(Annotated.class.getDeclaredMethods())
                .flatMap(method -> Arrays.stream(method.getAnnotations()))
                .collect(Collectors.toList());
        
        assertThat(methodAnnotations.toString(), is("[@java.lang.Deprecated(forRemoval=true, since=\"12\")]"));
        ```
    * get all deprecated annotations of all methods
        ```
        List<Deprecated> deprecatedAnnotations = Arrays.stream(Annotated.class.getDeclaredMethods())
                .map(method -> method.getAnnotation(Deprecated.class))
                .collect(Collectors.toList());
        
        assertThat(deprecatedAnnotations.size(), is(1));
        Deprecated deprecated = deprecatedAnnotations.get(0);
        
        assertThat(deprecated.forRemoval(), is(true));
        assertThat(deprecated.since(), is("12"));
        ```
    * get annotations of specific field
        ```
        Annotation[] annotations = Annotated.class.getDeclaredField("count").getAnnotations();
        
        assertThat(Arrays.toString(annotations), is("[@FieldAnnotation()]"));
        ```