package com.example.perfect_pitch_trainer.model

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.CertificatePinner;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;


class CustomTrust {

    private lateinit var client: OkHttpClient

    fun CustomTrust() {
        val trustManager: X509TrustManager
        val sslSocketFactory: SSLSocketFactory
        try {
            trustManager = trustManagerForCertificates(trustedCertificatesInputStream())
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, arrayOf<TrustManager>(trustManager), null)
            sslSocketFactory = sslContext.getSocketFactory()
        } catch (e: GeneralSecurityException) {
            throw RuntimeException(e)
        }

        client = OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustManager)
            .build()
    }

    @Throws(Exception::class)
    fun run() {
        val request = Request.Builder()
            .url("https://publicobject.com/helloworld.txt")
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            val responseHeaders = response.headers()
            for (i in 0 until responseHeaders.size()) {
                println(responseHeaders.name(i) + ": " + responseHeaders.value(i))
            }

            System.out.println(response.body()?.string())
        }
    }

    /**
     * Returns an input stream containing one or more certificate PEM files. This implementation just
     * embeds the PEM files in Java strings; most applications will instead read this from a resource
     * file that gets bundled with the application.
     */
    private fun trustedCertificatesInputStream(): InputStream {
        // PEM files for root certificates of Comodo and Entrust. These two CAs are sufficient to view
        // https://publicobject.com (Comodo) and https://squareup.com (Entrust). But they aren't
        // sufficient to connect to most HTTPS sites including https://godaddy.com and https://visa.com.
        // Typically developers will need to get a PEM file from their organization's TLS administrator.
        val comodoRsaCertificationAuthority = (""
                + "-----BEGIN CERTIFICATE-----\n"
                + "MIICXAIBAAKBgQDS3fsW6+lI7igFYjHApl3tKxV7XF+EnNNfFR1WzINePHk8FSwB\n" +
                "7IR8+0rE3Q8DomqhvKc3c5tTdLaIdkIGTVSC8mOmZqBf5rxtTuQiEFQDrqmaoEYp\n" +
                "agMyS+bfPPJZy6AR1W+vRvH/rMwKB0KAc4E8b6LcJEuhZgPvi7Hni8vdIQIDAQAB\n" +
                "AoGAQJeEsWnERPXGxpdknBmaDeszol0SWUHcsXR7TzpIR1aI4HGv0EsN7mbfaVw5\n" +
                "nChlN3PdWOwNuUu4Gib3ZIzNhNx9cBjnnMkXVRVuVTFxStis0+cbqVyEexJFUjWk\n" +
                "p9lTJ3K3Z59PBSSLGBNaU0fvUT/eEpfiIAuFyiA/0l/uFi0CQQD2EgSCHYUN6llh\n" +
                "KEvklUAXYu69u3Z04o1vC+q/fAWWRfB5ETPUcQ8GygiZVw+X9E/rrqxkdFK+gSGd\n" +
                "eLVFRVMXAkEA22BNe0irrjBGpacyFCW/N6ONCckqfoI93dULboS1BfB+gR9rGkah\n" +
                "nbQBX2cQrWfMySFckufsm3maESO5/MLUhwJALw269vaWHtcVSFLgUFGJ8+7jIDzc\n" +
                "pfU8fmOU/BDHMLknBr+XXaRQ70dq5YeUX8ZEEhlQtdTYjb07pFUUtK5Q+QJBAJbx\n" +
                "Uz6hiOcIdoOiX/W25zP8R53VGXUEXlcVMJFxpDCbAiAMIyBoOumMomknPDVkN2ao\n" +
                "ms7F32o2qEhkwaLl118CQCH9TLKH0PfzOkLUVIyJtDb3E+9lMnBwVuuUUQO+cVW/\n" +
                "9sVZXmlEFrYYue59FsJZO0LpVQU9GYd6ec/pG8fhU2o=\n"
                + "NVOFBkpdn627G190\n"
                + "-----END CERTIFICATE-----\n")
        val entrustRootCertificateAuthority = (""
                + "-----BEGIN CERTIFICATE-----\n"
                + "MIICazCCAdQCCQCts9iG00r0CDANBgkqhkiG9w0BAQsFADB6MQswCQYDVQQGEwJB\n" +
                "VTETMBEGA1UECAwKU29tZS1TdGF0ZTEhMB8GA1UECgwYSW50ZXJuZXQgV2lkZ2l0\n" +
                "cyBQdHkgTHRkMRIwEAYDVQQDDAlsb2NhbGhvc3QxHzAdBgkqhkiG9w0BCQEWEHRl\n" +
                "c3RAZXhhbXBsZS5jb20wHhcNMTkwMzEwMTA0NjE5WhcNMTkwNDA5MTA0NjE5WjB6\n" +
                "MQswCQYDVQQGEwJBVTETMBEGA1UECAwKU29tZS1TdGF0ZTEhMB8GA1UECgwYSW50\n" +
                "ZXJuZXQgV2lkZ2l0cyBQdHkgTHRkMRIwEAYDVQQDDAlsb2NhbGhvc3QxHzAdBgkq\n" +
                "hkiG9w0BCQEWEHRlc3RAZXhhbXBsZS5jb20wgZ8wDQYJKoZIhvcNAQEBBQADgY0A\n" +
                "MIGJAoGBANLd+xbr6UjuKAViMcCmXe0rFXtcX4Sc018VHVbMg148eTwVLAHshHz7\n" +
                "SsTdDwOiaqG8pzdzm1N0toh2QgZNVILyY6ZmoF/mvG1O5CIQVAOuqZqgRilqAzJL\n" +
                "5t888lnLoBHVb69G8f+szAoHQoBzgTxvotwkS6FmA++LseeLy90hAgMBAAEwDQYJ\n" +
                "KoZIhvcNAQELBQADgYEAiEc/G9RfHwI5r5m7p42mvRxVkdpU0PfSof7OsVuRrtjW\n" +
                "er78Pm6MGn1TEoOGdtsYHKMHq9hZjKPFgixkyGqTkEYSun4X+Y+RyoxNn6bakWkM\n" +
                "6/w6KVWLI8x2uHsUl8VIejO2+s8h4WD57UpcDy8/P/Ofw9NGoEBgvFxFOQfria8=\n"
                + "-----END CERTIFICATE-----\n")
        return Buffer()
            .writeUtf8(comodoRsaCertificationAuthority)
            .writeUtf8(entrustRootCertificateAuthority)
            .inputStream()
    }

    /**
     * Returns a trust manager that trusts `certificates` and none other. HTTPS services whose
     * certificates have not been signed by these certificates will fail with a `SSLHandshakeException`.
     *
     *
     * This can be used to replace the host platform's built-in trusted certificates with a custom
     * set. This is useful in development where certificate authority-trusted certificates aren't
     * available. Or in production, to avoid reliance on third-party certificate authorities.
     *
     *
     * See also [CertificatePinner], which can limit trusted certificates while still using
     * the host platform's built-in trust store.
     *
     * <h3>Warning: Customizing Trusted Certificates is Dangerous!</h3>
     *
     *
     * Relying on your own trusted certificates limits your server team's ability to update their
     * TLS certificates. By installing a specific set of trusted certificates, you take on additional
     * operational complexity and limit your ability to migrate between certificate authorities. Do
     * not use custom trusted certificates in production without the blessing of your server's TLS
     * administrator.
     */
    @Throws(GeneralSecurityException::class)
    private fun trustManagerForCertificates(`in`: InputStream): X509TrustManager {
        val certificateFactory = CertificateFactory.getInstance("X.509")
        val certificates = certificateFactory.generateCertificates(`in`)
        if (certificates.isEmpty()) {
            throw IllegalArgumentException("expected non-empty set of trusted certificates")
        }

        // Put the certificates a key store.
        val password = "password".toCharArray() // Any password will work.
        val keyStore = newEmptyKeyStore(password)
        var index = 0
        for (certificate in certificates) {
            val certificateAlias = Integer.toString(index++)
            keyStore.setCertificateEntry(certificateAlias, certificate)
        }

        // Use it to build an X509 trust manager.
        val keyManagerFactory = KeyManagerFactory.getInstance(
            KeyManagerFactory.getDefaultAlgorithm()
        )
        keyManagerFactory.init(keyStore, password)
        val trustManagerFactory = TrustManagerFactory.getInstance(
            TrustManagerFactory.getDefaultAlgorithm()
        )
        trustManagerFactory.init(keyStore)
        val trustManagers = trustManagerFactory.getTrustManagers()
        if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
            throw IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers))
        }
        return trustManagers[0] as X509TrustManager
    }

    @Throws(GeneralSecurityException::class)
    private fun newEmptyKeyStore(password: CharArray): KeyStore {
        try {
            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            val `in`: InputStream? = null // By convention, 'null' creates an empty key store.
            keyStore.load(`in`, password)
            return keyStore
        } catch (e: IOException) {
            throw AssertionError(e)
        }

    }
}