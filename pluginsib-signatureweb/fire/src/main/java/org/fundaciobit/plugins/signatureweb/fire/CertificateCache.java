package org.fundaciobit.plugins.signatureweb.fire;

import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CertificateCache {

    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<String, CacheEntry>();

    public CertificateCache() {
    }

    public List<X509Certificate> getCachedCertificates(String username) {
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

    public void setCachedCertificates(String username, List<X509Certificate> certificates) {
        CacheEntry cacheEntry = new CacheEntry(certificates);
        cache.put(username, cacheEntry);
    }

    public static class CacheEntry {

        private final List<X509Certificate> certificates;
        private final long expiryTime;

        public CacheEntry(List<X509Certificate> certificates) {
            if (certificates == null) throw new IllegalArgumentException("certificates is null");
            this.certificates = Collections.unmodifiableList(certificates);
            expiryTime = System.currentTimeMillis() + 3600000;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }

        public List<X509Certificate> getCertificates() {
            return certificates;
        }
    }
}
