package saboroso.saborosoburguer.models;

import java.util.List;

public record Message(
        String message,
        List<String> changes
) {
}
