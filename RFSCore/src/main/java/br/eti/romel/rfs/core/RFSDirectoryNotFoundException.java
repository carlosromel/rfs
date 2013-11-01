/**
 * Copyright (c) 2013 Carlos Romel Pereira da Silva <carlos.romel@gmail.com>
 */
package br.eti.romel.rfs.core;

/**
 *
 * @author Carlos Romel Pereira da Silva <carlos.romel@gmail.com>
 */
public class RFSDirectoryNotFoundException extends Exception {

    /**
     * Creates a new instance of
     * <code>RFSConfigException</code> without detail message.
     */
    public RFSDirectoryNotFoundException() {
    }

    /**
     * Constructs an instance of
     * <code>RFSConfigException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public RFSDirectoryNotFoundException(String msg) {
        super(msg);
    }
}
