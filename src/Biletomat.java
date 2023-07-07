

import java.time.LocalDate;
import java.util.*;

public class Biletomat {
    int[] tab_nom;
    // Przyklad kompozycji, klasa Biletomat sklada sie z listy wykonych tranzakcji
    List<TransactionInfo> tranzakcje;

    public Biletomat(){
        tranzakcje = new ArrayList<TransactionInfo>();
        tab_nom = new int[] {1,2,5,10,20,50,100,200,500,1000,2000,5000,10000,20000,50000};
    }

    public String toString() {
        String _tranzakcje = "";
        for(TransactionInfo tranzakcja:tranzakcje) {
            _tranzakcje += tranzakcja.toString();
            _tranzakcje += "==========\n";
        }
        return _tranzakcje;
    }

    public String wydrukujTranzakcje(LocalDate data) {
        String _tranzakcje = "";
        for(TransactionInfo tranzakcja:tranzakcje) {
            if(tranzakcja.getDate().equals(data)) {
                _tranzakcje += tranzakcja.toString();
                _tranzakcje += "==========\n";
            }
        }

        return _tranzakcje;
    }

    public void tranzakcja(LocalDate data) {
        TransactionInfo tranzakcja = new TransactionInfo();
        tranzakcja.setDate(data);
        boolean done = false;
        boolean isbilet = false;

        int option;
        while(!done) {
            System.out.println("Wybierz co chcesz zrobic:");
            System.out.println("1 - Kup bilet");
            System.out.println("2 - Wyjdz");
            if(isbilet) System.out.println("3 - Podsumowanie i platnosc");
            Scanner myObj = new Scanner(System.in);
            option = myObj.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Wybrano opcje zakupu biletu");
                    tranzakcja.addBilet(_tranzakcja());
                    isbilet = true;
                    break;
                case 2:
                    System.out.println("Dziekujemy za korzystanie z biletomatu");
                    done = true;
                    break;
                case 3:
                    if(isbilet) {
                        System.out.println("Podsumowanie:");
                        System.out.println(tranzakcja.toString());
                        System.out.println("W sumie " + tranzakcja.getTotalStr() + " do zaplaty");
                        System.out.println("1 - Platnosc");
                        System.out.println("2 - Powrot");
                        option = myObj.nextInt();
                        if(option == 1) {
                            done = platnosc(tranzakcja);
                        }
                    }
                    else {
                        System.out.println("Nieznana opcja");
                    }
                    break;
                default:
                    System.out.println("Nieznana opcja");
                    break; }

        }
    }

    private BiletInfo _tranzakcja() {
        Bilet nowy = null;
        int amount;
        System.out.println("Wybierz rodzaj biletu");
        System.out.println("1 - jednoprzejazdowy");
        System.out.println("2 - czasowy");
        System.out.println("3 - okresowy");
        Scanner myObj = new Scanner(System.in);
        String option = myObj.nextLine();
        switch (option) {
            case "1":
                nowy = new Bilet_jednoprzejazdowy("");
                break;
            case "2":
                System.out.println("Wybierz typ biletu czasowego");
                System.out.println("1 - 3h");
                System.out.println("2 - 6h");
                System.out.println("3 - 12h");
                System.out.println("4 - 24h");
                option = myObj.nextLine();
                switch(option) {
                    case "1":
                        nowy = new Bilet_czasowy("3h");
                        break;
                    case "2":
                        nowy = new Bilet_czasowy("6h");
                        break;
                    case "3":
                        nowy = new Bilet_czasowy("12h");
                        break;
                    case "4":
                        nowy = new Bilet_czasowy("24h");
                        break;
                }


                break;

            case "3":
                System.out.println("Wybierz typ biletu okresowego");
                System.out.println("1 - miesieczny");
                System.out.println("2 - kwartalny");
                System.out.println("3 - polroczny");
                System.out.println("4 - roczny");
                option = myObj.nextLine();
                switch(option) {
                    case "1":
                        nowy = new Bilet_okresowy("1m");
                        break;
                    case "2":
                        nowy = new Bilet_okresowy("3m");
                        break;
                    case "3":
                        nowy = new Bilet_okresowy("6m");
                        break;
                    case "4":
                        nowy = new Bilet_czasowy("1r");
                        break;
                }
                break;
        }
        System.out.println("Wybierz ilosc biletu");
        amount = Integer.parseInt(myObj.nextLine());
        BiletInfo _nowy = new BiletInfo(nowy,amount);
        return _nowy;
    }

    private boolean find_nominal(int wartosc) {
        for(int nominal:tab_nom) {
            if(wartosc == nominal) return true;
        }
        System.out.println("Nieistniejacy nominal");
        return false;
    }

    private void wydaj_reszte(int topay) {
        int wartosc;
        while(topay < 0) {
            wartosc = 0;
            for(int i = 0; i < tab_nom.length;i++) {
                if((tab_nom[i] <= -1*topay) && (tab_nom[i] > wartosc)) wartosc = tab_nom[i];
            }
            Gotowka gotowka = new Gotowka(wartosc);
            System.out.print("[" + gotowka.toString() + "] ");
            topay += wartosc;
        }
        System.out.print("\n");
    }

    private boolean platnosc(TransactionInfo tranzakcja) {
        int topay = tranzakcja.getTotal();
        System.out.println("Wybierz metode platnosci");
        System.out.println("1 - karta");
        System.out.println("2 - gotowka");
        Scanner myObj = new Scanner(System.in);
        int option = myObj.nextInt();
        switch(option) {
            case 1:
                System.out.println("Wpisz ilosc srodkow na karcie w groszach, do zaplaty:" + topay + "gr");
                option = myObj.nextInt();
                Pieniadz karta = new Karta(option);
                if(karta.getWartosc() < topay) {
                    System.out.println("Niewystarczajaca ilosc srodkow na karcie");
                    return false;
                }
                else {
                    System.out.println("Platnosc przeszla pomyslnie");
                    tranzakcje.add(tranzakcja);
                    return true;
                }
            case 2:
                while(true) {
                    System.out.println("Wpisz wartosc wrzucanego nominalu w groszach, lub -1 aby opuscic, do zaplaty:" + topay + "gr");
                    option = myObj.nextInt();
                    if(option == -1) return false;
                    else {
                        if(find_nominal(option)) {
                            topay -= option;
                        }
                    }
                    if(topay == 0) {
                        System.out.println("Platnosc przeszla pomyslnie");
                        return true;
                    }
                    else if (topay < 0) {
                        System.out.println("Platnosc przeszla pomyslnie");
                        System.out.println("Wydana reszta: ");
                        wydaj_reszte(topay);
                        tranzakcje.add(tranzakcja);
                        return true;
                    }
                }
        }
        return false;

    }

}

class BiletInfo {
    BiletInfo(Bilet bilet, int amount){
        this.bilet = bilet;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return bilet.getprice();
    }

    int amount;
    Bilet bilet;
    public String toString() {
        return bilet.toString() + " x" + amount + " tot: " + (amount*bilet.getprice())/100 + "." + String.format("%02d",(amount*bilet.getprice())%100) + "zl";
    }
}

class TransactionInfo{

    TransactionInfo(){
        lista_bil = new ArrayList <BiletInfo>();
        total = 0;
        data = LocalDate.now();
    }

    LocalDate getDate() {
        return data;
    }

    void setDate(LocalDate data) {
        this.data = data;
    }

    LocalDate data;
    int total;
    // Przyklad kompozycji, klasa TransactionInfo sklada sie z listy obiektow BiletInfo
    List<BiletInfo> lista_bil;

    public String getTotalStr() {
        return total/100 + "." + String.format("%02d", total%100) + "zl";
    }

    public int getTotal() {
        return total;
    }

    public void addBilet(BiletInfo bilet) {
        lista_bil.add(bilet);
        total += bilet.getAmount() * bilet.getPrice();
    }

    public String toString() {
        String info = "";
        for(BiletInfo bilet:lista_bil) {
            info += data.toString() + " " + bilet.toString() + "\n";
        }
        return info;
    }
}


