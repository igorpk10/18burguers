package br.com.eighteenburguers.core.domain;

public class Customer {
    
    private Long id;
    private final Document document;
    private final String name;
    private String email;

    public Customer(Document document, String name, String email) {
        this.document = document;
        this.name = name;
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
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
