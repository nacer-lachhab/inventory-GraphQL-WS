package ma.nacer.inventoryservice.dto;

public record ProductRequest (
        String name,
        double price,
        int quantity,
        long idCategory){}
