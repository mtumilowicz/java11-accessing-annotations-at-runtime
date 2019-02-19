/**
 * Created by mtumilowicz on 2019-02-19.
 */
@ClassAnnotation("new")
class Annotated {
    
    @FieldAnnotation
    private int count;
    
    @Deprecated(forRemoval = true, since = "12")
    void go() {
    }
}
