

import java.time.LocalDate;
import java.time.Month;


public class Main {

    public static void main(String[] args) {
        Biletomat Bill = new Biletomat();
        Bill.tranzakcja(LocalDate.now());
        System.out.println("Wszystkie Tranzakcje:");
        System.out.println(Bill.toString());
        System.out.println("Tranzakcje z dzisiaj:");
        System.out.println(Bill.wydrukujTranzakcje(LocalDate.now()));
        LocalDate data = LocalDate.of(2020, Month.APRIL, 25);
        Bill.tranzakcja(data);
        System.out.println("Wszystkie Tranzakcje:");
        System.out.println(Bill.toString());
        System.out.println("Tranzakcje z dzisiaj:");
        System.out.println(Bill.wydrukujTranzakcje(LocalDate.now()));
    }

}