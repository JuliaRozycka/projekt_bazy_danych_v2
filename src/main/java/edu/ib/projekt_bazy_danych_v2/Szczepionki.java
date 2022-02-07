package edu.ib.projekt_bazy_danych_v2;

import java.util.*;

public class Szczepionki {
    Map<String, ArrayList<String>> hashmap = new HashMap<String, ArrayList<String>>();

    public Szczepionki(Map<String, ArrayList<String>> hashmap) {
        this.hashmap = hashmap;
    }

    public Szczepionki() {
    }

    public Map<String, ArrayList<String>> getHashmap() {

        ArrayList<String> blonica = new ArrayList<String>();
        blonica.add("Adacel");
        blonica.add("Tdap");
        blonica.add("Tdap-IPV");
        blonica.add("Boostrix Polio");
        blonica.add("Clodivac");
        blonica.add("Hexacima");
        blonica.add("Infranix IPV");
        blonica.add("Tetraxim");
        hashmap.put("Błonica", blonica);

        ArrayList<String> cholera = new ArrayList<String>();
        cholera.add("Dukoral");
        hashmap.put("Cholera", cholera);

        ArrayList<String> covid = new ArrayList<String>();
        covid.add("Comirnaty (Pfizer)");
        covid.add("Spikevax (Moderna)");
        covid.add("Vaxzevria (AstraZeneca)");
        covid.add("Johnson & Johnson");
        covid.add("Nuvaxovid (Novavax)");
        hashmap.put("COVID-19", covid);

        ArrayList<String> dur = new ArrayList<String>();
        dur.add("Typhim Vi");
        dur.add("TyT");
        dur.add("Vivotif");
        hashmap.put("Dur brzuszny", dur);

        ArrayList<String> grypa = new ArrayList<String>();
        grypa.add("Influvac Tetra");
        grypa.add("Vaxigrip Tetra");
        grypa.add("Fluenz Tetra");
        hashmap.put("Grypa", grypa);

        ArrayList<String> gruzlica = new ArrayList<String>();
        gruzlica.add("BCG10");
        hashmap.put("Gruźlica", gruzlica);

        ArrayList<String> hpv = new ArrayList<String>();
        hpv.add("Cervarux");
        hpv.add("Gardasil");
        hpv.add("Gardasil 9");
        hashmap.put("HPV", hpv);

        ArrayList<String> krztusiec = new ArrayList<String>();
        krztusiec.add("Adacel");
        krztusiec.add("Boostrix");
        krztusiec.add("Boostrix Polio");
        krztusiec.add("Hexacima");
        krztusiec.add("Pentaxim");
        hashmap.put("Krztusiec", krztusiec);

        ArrayList<String> odra = new ArrayList<String>();
        odra.add("M-M-RVAXPRO");
        odra.add("Priorix");
        hashmap.put("Odra", odra);

        ArrayList<String> ospa = new ArrayList<String>();
        ospa.add("Varilrix");
        ospa.add("Varivax");

        hashmap.put("Ospa wietrzna", ospa);

        ArrayList<String> rozyczka = new ArrayList<String>();
        rozyczka.add("M-M-RVAXPRO");
        rozyczka.add("Priorix");
        hashmap.put("Różyczka", rozyczka);

        ArrayList<String> swinka = new ArrayList<String>();
        swinka.add("M-M-RVAXPRO");
        swinka.add("Priorix");
        hashmap.put("Świnka", swinka);

        ArrayList<String> tezec = new ArrayList<String>();
        tezec.add("Adacel");
        tezec.add("Boostrix");
        tezec.add("Dultavax");
        tezec.add("Infranix IPV");
        tezec.add("Tetana");
        hashmap.put("Tężec", tezec);

        ArrayList<String> wscieklizna = new ArrayList<String>();
        wscieklizna.add("Rabipur");
        wscieklizna.add("Verorab");
        hashmap.put("Wścieklizna", wscieklizna);

        ArrayList<String> wzwa = new ArrayList<String>();
        wzwa.add("Avaxim 160 U");
        wzwa.add("Havrix");
        wzwa.add("Twinrix");
        wzwa.add("Havrix Junior");
        hashmap.put("WZWA", wzwa);

        ArrayList<String> wzwb = new ArrayList<String>();
        wzwb.add("Engerix B");
        wzwb.add("Euvax");
        wzwb.add("Twinrix");
        hashmap.put("WZWB", wzwb);

        return hashmap;
    }

    public ArrayList getKeys(){
        Set<String> keySet = hashmap.keySet();
        ArrayList<String> listOfKeys = new ArrayList<String>(keySet);
        return listOfKeys;
    }

    public ArrayList getValuesForKey(String key){
        ArrayList<String> valueList= hashmap.get(key);
        return  valueList;
    }

    public static void main(String[] args) {
        Szczepionki szczepionki = new Szczepionki();
        System.out.println(szczepionki.getHashmap());
        System.out.println(szczepionki.getKeys());
        System.out.println(szczepionki.getValuesForKey("Odra"));
    }

    @Override
    public String toString() {
        return "Szczepionki{" +
                "hashmap=" + hashmap +
                '}';
    }
}
