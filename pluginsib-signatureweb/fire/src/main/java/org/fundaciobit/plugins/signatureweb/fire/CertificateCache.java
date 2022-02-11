package org.fundaciobit.plugins.signatureweb.fire;

import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Cache de certificats per emprar amb el Plugin FIRe.
 * Els mètodes per accedir al cache estan sincronitzats.
 */
public class CertificateCache {

    private final int timeToLive;
    private final Map<String, CacheEntry> cache;

    /**
     * Construeix un nou cache de certificats
     * @param maxSize tamany màxim del cache.
     * @param timeToLive duració de les entrades al cache en segons.
     */
    public CertificateCache(final int maxSize, final int timeToLive) {
        this.timeToLive = timeToLive;
        this.cache = new LinkedHashMap<String, CacheEntry>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, CacheEntry> eldest) {
                return size() > maxSize;
            }
        };
    }

    public synchronized List<X509Certificate> getCachedCertificates(String username) {
        CacheEntry cacheEntry = cache.get(username);
        if (cacheEntry == null) {
            return null;
        }
        if (cacheEntry.isExpired()) {
            cache.remove(username);
            return null;
        }
        return cacheEntry.getCertificates();
    }

    public synchronized void setCachedCertificates(String username, List<X509Certificate> certificates) {
        CacheEntry cacheEntry = new CacheEntry(certificates, timeToLive);
        cache.put(username, cacheEntry);
    }

    private static class CacheEntry {

        private final List<X509Certificate> certificates;
        private final long expiryTime;

        public CacheEntry(List<X509Certificate> certificates, int timeToLive) {
            if (certificates == null) throw new IllegalArgumentException("certificates is null");
            this.certificates = Collections.unmodifiableList(certificates);
            expiryTime = System.currentTimeMillis() + (timeToLive * 1000L);
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }

        public List<X509Certificate> getCertificates() {
            return certificates;
        }
    }
}
