/**
 * Copyright (c) 2013 Carlos Romel Pereira da Silva <carlos.romel@gmail.com>
 */
package br.eti.romel.rfs.core;

import java.util.*;

/**
 *
 * @author Carlos Romel Pereira da Silva <carlos.romel@gmail.com>
 */
public class RFSFileType {

    private UUID id;
    private String description;

    public RFSFileType() {
    }

    public RFSFileType(UUID id, String description) {
        this.id = id;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
