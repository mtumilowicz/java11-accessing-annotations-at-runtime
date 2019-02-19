import java.util.LinkedList;
import java.util.List;

/**
 * Created by mtumilowicz on 2019-02-19.
 */
@Annotation1
public class Annotated {
    
    private List field = new LinkedList<String>();

    @SuppressWarnings("unchecked")
    @Deprecated
    void go() {
        field.add(1);
    }
}
