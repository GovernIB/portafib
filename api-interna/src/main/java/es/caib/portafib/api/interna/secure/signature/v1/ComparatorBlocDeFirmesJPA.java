package es.caib.portafib.api.interna.secure.signature.v1;

import java.util.Comparator;

import es.caib.portafib.persistence.BlocDeFirmesJPA;

/**
 * 
 * @author anadal
 * 3 jun 2025 11:45:24
 */
public class ComparatorBlocDeFirmesJPA implements Comparator<BlocDeFirmesJPA> {

    @Override
    public int compare(BlocDeFirmesJPA o1, BlocDeFirmesJPA o2) {

        return o1.getOrdre() - o2.getOrdre();
    }

}
