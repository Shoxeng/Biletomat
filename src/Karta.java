

//Przyklad dziedziczenia, klasa karta dziedziczy po klasie pieniadz
public class Karta extends Pieniadz {
    void onCreate(int wartosc){
        if(wartosc >= 0 ) this.wartosc = wartosc;
        else this.wartosc = 0;
    }

    public Karta(int wartosc){
        super(wartosc);
    }
}
