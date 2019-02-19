# java11-accessing-annotations-at-runtime
_Reference_: https://www.amazon.com/Java-Language-Features-Modules-Expressions/dp/1484233476
_Reference_: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/reflect/AnnotatedElement.html

# preface
* https://github.com/mtumilowicz/java-annotations-retention-policy
* we are able to access only annotations, which have runtime retention policy
* annotations are Java objects
* `java.lang.reflect.AnnotatedElement`
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
