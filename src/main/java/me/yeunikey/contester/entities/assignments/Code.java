package me.yeunikey.contester.entities.assignments;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Code {

    @Id
    private String uniqueId = UUID.randomUUID().toString();

    @Column(name = "language")
    private LanguageType languageType;

    @Column(name = "bytes")
    private byte[] bytes;

    public Code() {
    }

    public Code(LanguageType languageType, byte[] bytes) {
        this.languageType = languageType;
        this.bytes = bytes;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
