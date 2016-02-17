package br.com.solutiolicita.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matheus Oliveira
 */
public class Criptografar {

    private final String algoritmo;
    private final String encoding;
    private MessageDigest md;
    private static final Logger log = Logger.getLogger(Criptografar.class.getName());
    private static Criptografar cript;

    private Criptografar() {
        this.algoritmo = "MD5";
        this.encoding = "UTF-8";
    }

    public String criptografar(String senha) {
        try {
            md = MessageDigest.getInstance(this.algoritmo);
            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes(this.encoding)));
            log.info("Criptografia Realizada com sucesso!");
            return hash.toString(32);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            log.log(Level.SEVERE, null, e);
            return null;
        }

    }

    public static Criptografar getInstance() {
        if (Criptografar.cript == null) {
            return new Criptografar();
        } else {
            return Criptografar.cript;
        }
    }
}
