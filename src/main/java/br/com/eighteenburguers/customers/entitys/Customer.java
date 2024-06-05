package br.com.eighteenburguers.customers.entitys;

public class Customer {
    
    private String id;
    private final Document document;
    private final String name;
    private String email;

    public Customer(Document document, String name, String email) {
        this.document = document;
        this.name = name;
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public Document getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
