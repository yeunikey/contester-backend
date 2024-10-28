package me.yeunikey.contester.entities.assignments;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "codes")
public class Code {

    @Id
    private String uniqueId = UUID.randomUUID().toString();

    @Column(name = "language")
    private LanguageType languageType;

    @Lob
    @Column(name = "bytes")
    private byte[] bytes;

    @Column(name = "status")
    private CodeStatus status = CodeStatus.NOT_CHECKED;

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

    public CodeStatus getStatus() {
        return status;
    }

    public void setStatus(CodeStatus status) {
        this.status = status;
    }
}
