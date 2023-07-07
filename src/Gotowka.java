

// przyklad dziedziczenia, klasa gotowka dziedziczy po pieniadzu
public class Gotowka extends Pieniadz {
    void onCreate(int wartosc) {
        switch(wartosc) {
            case 1:
                this.wartosc = 1;
                break;
            case 2:
                this.wartosc = 2;
                break;
            case 5:
                this.wartosc = 5;
                break;
            case 10:
                this.wartosc = 10;
                break;
            case 20:
                this.wartosc = 20;
                break;
            case 50:
                this.wartosc = 50;
                break;
            case 100:
                this.wartosc = 100;
                break;
            case 200:
                this.wartosc = 200;
                break;
            case 500:
                this.wartosc = 500;
                break;
            case 1000:
                this.wartosc = 1000;
                break;
            case 2000:
                this.wartosc = 2000;
                break;
            case 5000:
                this.wartosc = 5000;
                break;
            case 10000:
                this.wartosc = 10000;
                break;
            case 20000:
                this.wartosc = 20000;
                break;
            case 50000:
                this.wartosc = 50000;
                break;
            default:
                this.wartosc = 0;
                break;
        }
    }

    public Gotowka(int wartosc){
        super(wartosc);
    }
}