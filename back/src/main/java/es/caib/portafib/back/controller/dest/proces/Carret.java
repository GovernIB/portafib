package es.caib.portafib.back.controller.dest.proces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Carret implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Map<Long, Long> estatsPeticions;
    private final List<Long> estatsProcessar;
    private int index;

    private final Set<Long> estatsFirmar = new HashSet<Long>();
    private final Map<Long, String> estatsRebutjar = new HashMap<Long, String>();

    public Carret(Map<Long, Long> estatsPeticions) {
        if (estatsPeticions == null || estatsPeticions.isEmpty()) {
            throw new IllegalArgumentException("estatsPeticions no pot ser null o buid");
        }
        this.estatsPeticions = Collections.unmodifiableMap(estatsPeticions);
        this.estatsProcessar = Collections.unmodifiableList(new ArrayList<Long>(estatsPeticions.keySet()));
        this.index = 0;
    }

    public int getPosition() {
        return index + 1;
    }

    public int getTotal() {
        return estatsProcessar.size();
    }

    public Long getEstat() {
        return estatsProcessar.get(index);
    }

    public Long getPeticio() {
        return estatsPeticions.get(getEstat());
    }

    public List<Long> getEstatsProcessar() {
        return estatsProcessar;
    }

    // Métodes de navegació per anar envant i enrera
    public boolean hasNext() {
        return getPosition() < estatsProcessar.size();
    }

    public void next() {
        if (hasNext()) index++;
    }

    // Métdoes per afegir o llevar o consultar peticions de firma

    public void addEstatFirmar() {
        estatsFirmar.add(getEstat());
    }

    public Set<Long> getEstatsFirmar() {
        return Collections.unmodifiableSet(estatsFirmar);
    }

    // Mètodes per afegir o llevar o consultar peticions de rebuig

    public void addEstatRebuig(String motiuRebuig) {
        if (motiuRebuig == null ||motiuRebuig.isEmpty()) {
            throw new IllegalArgumentException("Motiu de rebuig requerit");
        }

        estatsRebutjar.put(getEstat(), motiuRebuig);
    }

    public Map<Long, String> getEstatsRebuig() {
        return Collections.unmodifiableMap(estatsRebutjar);
    }

    public Map<Long, Long> getEstatsPeticions() {
        return estatsPeticions;
    }

    public int getEstatsIgnorats() {
        return getTotal() - getEstatsFirmar().size() - getEstatsRebuig().keySet().size();
    }
}
