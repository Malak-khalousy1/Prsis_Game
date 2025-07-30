public class Throw {
    public boolean[] shell = new boolean[6];
    //true=>up
    //false=>down

    public Throw() {
        for (int i = 0; i < shell.length; i++) {
            shell[i] = Math.random() > 0.4 ? true : false;
        }
    }
}
