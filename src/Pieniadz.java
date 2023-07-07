

abstract public class Pieniadz {
    int wartosc;
    void onCreate(int wartosc) {}

    public int getWartosc() {
        return wartosc;
    }

    public Pieniadz(int wartosc){
        onCreate(wartosc);
    };

    public String toString() {
        return wartosc/100 + "." + String.format("%02d", wartosc%100) + "zl";
    }
}