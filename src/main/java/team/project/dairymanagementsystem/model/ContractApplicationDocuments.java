package team.project.dairymanagementsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "ContractApplicationDocuments")
public class ContractApplicationDocuments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "documentName", nullable = false)
    private String documentName;

    @Lob
    @Column(name = "document", nullable = false)
    private byte[] document;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "application")
    private ContractApplication application;

    public ContractApplicationDocuments() {
    }

    public ContractApplicationDocuments(String documentName, byte[] document, ContractApplication application) {
        this.documentName = documentName;
        this.document = document;
        this.application = application;
    }


    public ContractApplication getApplication() {
        return application;
    }

    public void setApplication(ContractApplication application) {
        this.application = application;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "ContractApplicationDocuments{" +
                "documentName='" + documentName + '\'' +
                '}';
    }
}
