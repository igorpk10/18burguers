package br.com.eighteenburguers.customers.entitys;

public class Document {

    private final DocumentType type;
    private final String number;
    
    public Document(DocumentType type, String number) {
        this.type = type;
        this.number = number;
    }

    public DocumentType getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }
    
}
