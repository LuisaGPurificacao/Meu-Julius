package br.com.fiap.meujulius.models;

public record Token(
    String token,
    String type,
    String prefix
) {
    
}
