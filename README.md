# java11-accessing-annotations-at-runtime
_Reference_: https://www.amazon.com/Java-Language-Features-Modules-Expressions/dp/1484233476
_Reference_: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/reflect/AnnotatedElement.html

# preface
* https://github.com/mtumilowicz/java-annotations-retention-policy
* https://github.com/mtumilowicz/java-reflection
* we are able to access only annotations, which have runtime retention policy
* annotations are Java objects
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
    * 