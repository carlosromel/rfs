/**
 * Copyright (c) 2013 Carlos Romel Pereira da Silva <carlos.romel@gmail.com>
 */
package br.eti.romel.rfs.core;

import com.thoughtworks.xstream.annotations.*;
import java.math.*;
import java.util.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author Carlos Romel Pereira da Silva <carlos.romel@gmail.com>
 */
@XmlRootElement
public class RFSInfo {

    @XStreamAsAttribute
    private String author;
    @XStreamAsAttribute
    private Long creationDate;
    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private String originalPath;
    @XStreamAsAttribute
    private String destinationPath;
    @XStreamAsAttribute
    private Long length;
    @XStreamAsAttribute
    private RFSFileType type;

    public RFSInfo() {
    }

    public String getAuthor() {
        return this.author;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    public Long getCreationDate() {
        return this.creationDate;
    }

    void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setFileName(String fileName) {
        this.name = fileName;
    }

    public String getDestinationPath() {
        return this.destinationPath;
    }

    void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public Long getLength() {
        return length;
    }

    public void setSize(Long size) {
        this.length = size;
    }

    public RFSFileType getType() {
        return type;
    }

    public void setType(RFSFileType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final String formato = ""
                + "RFSInfo{author.........:'%s',\n"
                + "        creationDate...:'%s',\n"
                + "        fileName.......:'%s',\n"
                + "        virtualPath....:'%s',\n"
                + "        destinationPath:'%s',\n"
                + "        size...........:'%s',\n"
                + "        type...........:'%s'\n"
                + "       }\n";
        return String.format(formato,
                             author,
                             creationDate,
                             name,
                             originalPath,
                             destinationPath,
                             length,
                             type);
    }
}
