package util;


/*@ This class is used to generate fresh names.
    For each object of this class, each time the
    [getNew()] method is called it returns a name
    it has never generated before.
    Several objects created with  the same prefix may
    generate identical names.
 */
public class Generator {

    private int counter;

    private String prefix;

    public String getNew(){
        String newName = prefix + counter;
        counter += 1;
        return newName;
    }

    public Generator(String prefix){
        this.counter = 0;
        this.prefix = prefix;
    }
}
