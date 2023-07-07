abstract public class Bilet {

    String typ;
    int price;
    protected void onCreate(String type) {};
    protected Bilet(String type){
        onCreate(type);
    };
    String gettype() {
        return typ;
    };
    int getprice() {
        return price;
    };
    public String toString() {
        return typ + " " + price/100 + "." + String.format("%02d",price%100) + "zl";
    };
}

//Przyklad dziedziczenia, klasa Bilet_jednoprzejazdowy dziedziczy atrybuty i metody klasy Bilet
class Bilet_jednoprzejazdowy extends Bilet {
    protected void onCreate(String type) {
        typ = "Bilet jednoprzejazdowy";
        price = 600;
    }

    Bilet_jednoprzejazdowy(String type){
        super(type);
    }
}

//Przyklad dziedziczenia, klasa Bilet_czasowy dziedziczy atrybuty i metody klasy Bilet
class Bilet_czasowy extends Bilet {
    protected void onCreate(String type) {
        switch(type) {
            case "3h":
                typ = "Bilet 3-godzinny";
                price = 800;
                break;
            case "6h":
                typ = "Bilet 6-godzinny";
                price = 1200;
                break;
            case "12h":
                typ = "Bilet 12-godzinny";
                price = 1600;
                break;
            case "24h":
                typ = "Bilet 24-godzinny";
                price = 2000;
                break;
            default:
                typ = "undefined";
                price = -1;
                break;
        }
    }

    protected Bilet_czasowy(String type){
        super(type);
    }
}

//Przyklad dziedziczenia, klasa Bilet_okresowy dziedziczy atrybuty i metody klasy Bilet
class Bilet_okresowy extends Bilet {
    protected void onCreate(String type) {
        switch(type) {
            case "1m":
                typ = "Bilet miesieczny";
                price = 10000;
                break;
            case "3m":
                typ = "Bilet kwartalowy";
                price = 25000;
                break;
            case "6m":
                typ = "Bilet polroczny";
                price = 40000;
                break;
            case "1r":
                typ = "Bilet roczny";
                price = 60000;
                break;
            default:
                typ = "undefined";
                price = -1;
                break;
        }
    }

    protected Bilet_okresowy(String type){
        super(type);
    }
}