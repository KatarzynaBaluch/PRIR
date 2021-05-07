/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab02_ksiazkatelefoniczna;


import java.util.*;

/**
 *
 * @author Marcin
 */
public class KsiazkaTelefoniczna implements IKsiazkaTelefoniczna {

    Map<Integer, IOsoba> mapId2Osoba=new HashMap<>();

    Map<String, List<Integer>> mapImie2Id=new HashMap<>();
    Map<String, List<Integer>> mapNazwisko2Id=new HashMap<>();



    @Override
    public Collection<IOsoba> getByImie(String imie) {
        List<IOsoba> wynik=new ArrayList<>();
        List<Integer> list=mapImie2Id.get(imie);
        for(int id:list)
        {
            IOsoba o=mapId2Osoba.get(id);
            wynik.add(o);
        }

        return wynik;
    }

    @Override
    public Collection<IOsoba> getByNazwisko(String nazwisko) {
        List<IOsoba> wynik=new ArrayList<>();
        List<Integer> list=mapNazwisko2Id.get(nazwisko);
        for(int id:list)
        {
            IOsoba o=mapId2Osoba.get(id);
            wynik.add(o);
        }

        return wynik;
    }

    @Override
    public IOsoba remove(int id) {
        IOsoba o=mapId2Osoba.remove(id);
        //1 sposób
        String imie=o.getImie();
        List<Integer> ids=mapImie2Id.get(imie);
        //ids.remove(id);
        ids.remove(Integer.valueOf(id)); //typ obiektowy

        // ids.remove(Integer.valueOf(id)); to samo remove(new Integer(id));

        //lepiej nie usuwac klucza jak nawet zostanie pusta lista

        //2 sposób
        mapNazwisko2Id.get(o.getNazwisko()).remove(Integer.valueOf(id));

        return o;


          }

    @Override
    public void addOsoba(IOsoba osoba) {
        int id=osoba.getId();
        mapId2Osoba.put(id, osoba);
        String imie=osoba.getImie();
        if(!mapImie2Id.containsKey(imie))
        {
            mapImie2Id.put(imie, new ArrayList<>());
        }
        List<Integer> ids=mapImie2Id.get(imie);
        ids.add(id);
   }

    @Override
    public int size() {
        Integer s=mapId2Osoba.size();
        return s;
    }


}
